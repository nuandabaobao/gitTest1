package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.entity.BackResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.*;
import com.tbjj.portal.core.bo.*;
import com.tbjj.portal.core.convert.*;
import com.tbjj.portal.repository.api.ContractRepository;
import com.tbjj.portal.repository.api.ContractTypeRepository;
import com.tbjj.portal.repository.api.EventRepository;
import com.tbjj.portal.repository.api.EventStreamRepository;
import com.tbjj.portal.repository.api.entity.Contract;
import com.tbjj.portal.repository.api.entity.ContractType;
import com.tbjj.portal.repository.api.entity.Event;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * Created by czhenzhen on 2017/12/28.
 */
@Service
public class WaitServiceImpl extends BaseServiceImpl implements WaitService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventStreamRepository eventStreamRepository;

    @Autowired
    private EventMessageService eventMessageService;

    @Autowired
    private EventStreamService eventStreamService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    /**
     * 明源添加申请
     * @param bo
     * @return
     */
    @Override
    public int addApply(ApplyBO bo) {
        logger.info("明源添加申请，入参："+bo);
        Event event = new Event();
        event = ApplyBOConvert.boToEntity(bo,event);
        int count = eventRepository.insertSelective(event);
        if (count < 1){
           // throw new ServiceException(1002,bo,"插入事件表失败");
        }
        return count;
    }

    /**
     * 明源添加待办事项
     * @param bo
     * @return
     */
    @Transactional
    @Override
    public int addWait(WaitBO bo) throws IOException {
        logger.info("明源添加待办事项，入参："+bo);
        if (null == bo){
           // throw new ServiceException(1001,bo,"请传入审批参数");
        }
        //根据bo里事件唯一标识查询事件表的主键id
        Event event = eventService.getEventByEventCode(bo.getEventId());
        if(event==null){
            return 3;
        }
        //判断事件完成之后便不再进行发起待办
        if(event.getEventStatus()==2){
            return 1;
        }
        Integer eventId = event.getId();
        //数据添加至事件流表
        EventStream eventStream = new EventStream();
        eventStream = WaitBOConvert.boToEntity(bo,eventStream);
        eventStream.setEventId(eventId);
        eventStream.setSystemCode("001");
        int count = eventStreamRepository.insertSelective(eventStream);
        //更新事件表最新操作时间
        Date operationTime = DateUtil.formatDate(bo.getOperationTime());
        eventService.upateOperateTimeById(eventId,operationTime);
        //判断是否是驳回之后修改，若是，修改事件表的修改次数
        if ("1".equals(bo.getIsUpdate())){
            //表明是修改
            Integer updateTimes = event.getUpdateTimes();
            eventService.upateEventTimesById(eventId,updateTimes+1);
        }
        //待办事项插入消息表并给相应的接收人发送消息
        EventMessageBO eventMessageBO = new EventMessageBO();
        eventMessageBO.setMessTitle(event.getTitle());
        eventMessageBO.setMessUrl(bo.getApproveUrl());
        eventMessageBO.setMessDate(eventStream.getOperationTime());
        eventMessageBO.setUserName(bo.getRevceier());
        eventMessageBO.setEventStreamId(eventStream.getId());
        eventMessageService.addMessage(eventMessageBO,bo.getRevceier());
        return count;
    }

    /**
     * 业务系统待办转已办
     * @param bo
     * @return
     */
    @Transactional
    @Override
    public int waitToFinish(WaitToFinishBO bo) {
        logger.info("待办转已办，入参："+bo);
        if (null == bo){
            //throw new ServiceException(1001,bo,"请传入待办转已办的参数");
        }
        //更新事件流表最新操作时间
        EventStream eventStream = new EventStream();
        eventStream.setBacklogId(bo.getBacklogId());
        eventStream.setOperationTime(bo.getOperationTime());
        //设置阅读状态和处理状态
        eventStream.setDealCode(1);
        eventStream.setReadCode(0);
        Integer count = eventStreamService.updateEventStreamByBacklogId(eventStream);
        if (count < 1){
            //throw new ServiceException(1002,count,"更新事件流表失败");
        }

        //根据bo里事件唯一标识查询事件表的主键id
        Event event = eventService.getEventByEventCode(bo.getEventId());
        Integer eventId = event.getId();
        //更新事件表最新操作时间
        Date operationTime = bo.getOperationTime();

        Event eventUpdate=new Event();
        eventUpdate.setId(eventId);
        eventUpdate.setOperationTime(operationTime);
        if("2".equals(bo.getIsArchive())){
            //表示是归档
            eventUpdate.setIsPass(1);
            eventUpdate.setEventStatus(2);
            eventUpdate.setReadStatus(0);
        }
        eventRepository.updateByPrimaryKeySelective(eventUpdate);
        return count;
    }

    @Override
    public BackResult test()  {
        BackResult backResult = new BackResult();
        //throw new ServiceException(1001,"1234");
        backResult.setErrorCode(1001);
        backResult.setMsg("12345");
        return backResult;
    }

    /**
     * 付款申请制单接口
     * @param createItemOrderBO
     */
    @Override
    public void createItemOrder(CreateItemOrderBO createItemOrderBO) {

        //根据名源传递的公司名称和合同类型名称查找到唯一的合同类型编码
        //首先创建一个查询条件的合同类型对象
        ContractType contractType=new ContractType();
        BillReqBO billReqBO = createItemOrderBO.getBillReqBO();
        String unitid = billReqBO.getUnitid();
        if("天津滨海开元房地产开发有限公司".equals(unitid)||"天津天保房地产开发有限公司".equals(unitid)||"天津天保福源房地产开发有限公司".equals(unitid)){
            contractType.setCompanyName("房产公司");
        }else if("天保基建股份有限公司".equals(unitid)){
            contractType.setCompanyName(unitid);
        }else if("天津市百利建设工程有限公司".equals(unitid)){
            contractType.setCompanyName(unitid);
        }else if("天津嘉创物业服务有限公司".equals(unitid)){
            contractType.setCompanyName(unitid);
        }else{
            throw new ServiceException(1001,null,"传入参数unitid------"+unitid+"错误");
        }
        if(StringUtils.isBlank(billReqBO.getContractType())){
            throw new ServiceException(1001,null,"未传入参数contractType");
        }
        contractType.setMyCode(billReqBO.getContractType());

        String jqCode = contractTypeRepository.getJqCode(contractType);
        if(StringUtils.isBlank(jqCode)){
            throw new ServiceException(1001,null,"该合同类型在财务系统不存在");
        }
        billReqBO.setContractType(jqCode);

        createItemOrderBO.setBillReqBO(billReqBO);

        //调用finance中调用财务系统的方法
        BillRespBO billRespBo = financeService.createBill(createItemOrderBO);

        if(billRespBo==null){
            throw new ServiceException(101,null,"调用财务制作单据接口错误");
        }

        if(billRespBo.getRtnCode()==10){
            //成功
            logger.info("调用财务系统处理状态返回信息描述："+billRespBo.getRtnMsg());
            Event event = eventService.getEventByEventCode(createItemOrderBO.getEventCode());
            if(event!=null){
                //更新事件最新操作时间
                event.setOperationTime(createItemOrderBO.getOperationTime());
                eventRepository.updateByPrimaryKeySelective(event);
            }else{
                //表明申请制单事件不存在
                //新建事件
                event=new Event();
                CreateOrderEventConvert.BOToEvent(event,createItemOrderBO);
                event.setApplyUrl(billRespBo.getBillUrl());
                eventRepository.insertSelective(event);
            }
            //新建待办事件流,这里需要生成一个申请人的代办
            EventStream eventStream=new EventStream();
            CreateOrderStreamConvert.BOToEventStream(eventStream,createItemOrderBO);
            eventStream.setEventId(event.getId());
            eventStream.setApproveUrl(billRespBo.getBillUrl());
            eventStreamRepository.insertSelective(eventStream);

            try {
                //新建消息
                EventMessageBO eventMessageBO = new EventMessageBO();
                CreateOrderMsgConvert.BOToEventMessage(eventMessageBO,createItemOrderBO);
                eventMessageBO.setMessUrl(billRespBo.getBillUrl());
                eventMessageBO.setEventStreamId(eventStream.getId());
                eventMessageService.addMessage(eventMessageBO, eventMessageBO.getUserName());
            }catch (Exception e){
                e.printStackTrace();
            }

            //新建合同
            Contract contract=new Contract();
            BillReqBOToContract.BOToConTract(contract,createItemOrderBO.getBillReqBO());
            contract.setEventId(event.getId());
            contract.setApplyDate(createItemOrderBO.getOperationTime());
            contractRepository.insertSelective(contract);
        }else{
            //失败
            logger.info("调用财务系统处理状态返回信息描述:"+billRespBo.getRtnMsg());
            throw new ServiceException(billRespBo.getRtnCode(),null,billRespBo.getRtnMsg());
        }
    }

}
