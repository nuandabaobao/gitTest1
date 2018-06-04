package com.tbjj.portal.core.impl;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.EventMessageService;
import com.tbjj.portal.core.EventService;
import com.tbjj.portal.core.FinanceService;
import com.tbjj.portal.core.bo.*;
import com.tbjj.portal.core.convert.ApproveEventBOConvert;
import com.tbjj.portal.core.convert.ApproveEventMsgBOConvert;
import com.tbjj.portal.core.convert.ApproveStreamBOConvert;
import com.tbjj.portal.core.mywebservice.HTFKService;
import com.tbjj.portal.core.mywebservice.HTFKServiceSoap;
import com.tbjj.portal.repository.api.ContractRepository;
import com.tbjj.portal.repository.api.EmployeeRepository;
import com.tbjj.portal.repository.api.EventRepository;
import com.tbjj.portal.repository.api.EventStreamRepository;
import com.tbjj.portal.repository.api.entity.Contract;
import com.tbjj.portal.repository.api.entity.ContractExample;
import com.tbjj.portal.repository.api.entity.Event;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.apache.axis.client.Call;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Service
public class FinanceServiceImpl extends BaseServiceImpl implements FinanceService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventStreamRepository eventStreamRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMessageService eventMessageService;

    @Value("${finance.wsdlurl}")
    private String financeWsdlUrl;

//    @Value("${result.resultUrl}")
//    private String approveResultUrl;
//
//    @Value("${payMoney.payMoneyUrl}")
//    private String payMoneyUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ContractRepository contractRepository;

    /**
     * 通知接口
     * @param approveMsgBO
     */
    @Override
    public void approveMsg(ApproveMsgBO approveMsgBO) {
        if("1".equals(approveMsgBO.getIsContract())){
            //表示是合同审批类型,调用合同审批类型的方法
            this.contractApprove(approveMsgBO);
        }else{
            //表示不是合同审批类型,调用非合同审批类型方法
            this.noContractApprove(approveMsgBO);
        }
    }

    /**
     * 撤销接口
     * @param revocationBO
     */
    @Override
    public void revocationMsg(RevocationBO revocationBO) {
        Event event = eventService.getEventByEventCode(revocationBO.getEventCode());
        if(event!=null){
            if("1".equals(revocationBO.getIsContract())){
                //表示是合同审批类型
                //需要通知业务系统审批结果,并且删除该事件
                event.setOperationTime(revocationBO.getOperationTime());
                event.setIsDelete((byte)1);
                event.setEventStatus(2);
                event.setReadStatus(0);
                eventRepository.updateByPrimaryKeySelective(event);
                //调用业务系统的审批结果接口方法
                //---------------------------------------------------------------------
                logger.info("参数："+revocationBO.getEventCode()+"---"+revocationBO.getOperationTime());
                //------------------------------------------------------------------------
                //---审批结果---
                //设置调用业务的参数
                HTFKService htfkService=new HTFKService();
                HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
                String result = htfkServiceSoap.approveResult(revocationBO.getEventCode(),null, DateUtil.getDateStr(revocationBO.getOperationTime(), "yyyy-MM-dd HH:mm:ss"), 2, null);

//                Map<String,Object> map=new HashMap<>();
//                map.put("eventId",revocationBO.getEventCode());
//                map.put("operationTime",DateUtil.getDateStr(revocationBO.getOperationTime(),"yyyy-MM-dd HH:mm:ss"));
//                String result = HttpClient.doPost(approveResultUrl, map);



                Map<String,Object> mapResult =(Map<String, Object>) JSON.parse(result);
                if(!"0".equals(mapResult.get("errorCode"))){
                    throw new ServiceException(1,null,"撤销接口调用业务系统接口错误");
                }
            }else{
                //表示不是合同审批类型,根据事件唯一标示查询事件,修改事件操作事件，修改事件是否删除标识
                event.setOperationTime(revocationBO.getOperationTime());
                event.setIsDelete((byte)1);
                event.setEventStatus(2);
                //设置完成状态的为未读
                event.setReadStatus(0);
                eventRepository.updateByPrimaryKeySelective(event);
            }
        }
    }

    /**
     *  财务实付款信息返回接口
     * @param realPayBO
     */
    @Override
    public void approvePayment(RealPayBO realPayBO) {
        //调用业务实付款信息的接口,将参数传递给业务的实付款信息接口,由调用业务系统的方法来进行修改是否完成
        logger.info("参数:"+"合同编号："+realPayBO.getContractCode()+"合同类型："+realPayBO.getContractType()
                +"款项名称："+realPayBO.getClauseItemName()+"结算方式："+realPayBO.getPayWay()+"实付金额："
                +realPayBO.getPayWay()+"付款日期："+realPayBO.getRealPayMoney()+"事件唯一标识："+realPayBO.getEventCode());
        //创建调用业务实付款信息需要的条件
        //--实付款接口---
//        Map<String,Object> map=new HashMap<>();
//        map.put("contractCode",realPayBO.getContractCode());
//        map.put("contractType",realPayBO.getContractType());
//        map.put("clauseItemName",realPayBO.getClauseItemName());
//        map.put("payWay",realPayBO.getPayWay());
//        map.put("realPayMoney",realPayBO.getRealPayMoney());
//        map.put("payMoneyDate",realPayBO.getPayMoneyDate());
//        map.put("eventId",realPayBO.getEventCode());
//        String result = HttpClient.doPost(payMoneyUrl, map);

        //根据事件唯一标识查询事件
        Event event = eventService.getEventByEventCode(realPayBO.getEventCode());

        //Contract contractByEventId=contractRepository.getContractByEventId(event.getId());

        // 创建XML文档树
        Document document = DocumentHelper.createDocument();
        Element business = document.addElement("business");
        Element fkApplyGUID = business.addElement("FkApplyGUID");
        if(StringUtils.isNotBlank(realPayBO.getEventCode())){
            fkApplyGUID.addText(realPayBO.getEventCode());
        }
        Element contractNo = business.addElement("ContractNo");
        if(StringUtils.isNotBlank(realPayBO.getContractCode())){
            contractNo.addText(realPayBO.getContractCode());
        }
        Element jbDate = business.addElement("JbDate");
        if(StringUtils.isNotBlank(realPayBO.getPayMoneyDate())){
            jbDate.addText(realPayBO.getPayMoneyDate());
        }
        Element items = business.addElement("Items");
        Element payItemEntity = items.addElement("PayItemEntity");
        Element itemName = payItemEntity.addElement("ItemName");
        if(StringUtils.isNotBlank(realPayBO.getClauseItemName())){
            itemName.addText(realPayBO.getClauseItemName());
        }
        Element realPayMoney = payItemEntity.addElement("RealPayMoney");
        if(StringUtils.isNotBlank(realPayBO.getRealPayMoney())){
            realPayMoney.addText(realPayBO.getRealPayMoney());
        }
        Element rate = payItemEntity.addElement("Rate");
        rate.addText("1");
        Element financeJsMode = payItemEntity.addElement("FinanceJsMode");
        if(StringUtils.isNotBlank(realPayBO.getPayWay())){
            financeJsMode.addText(realPayBO.getPayWay());
        }
        Element element = business.addElement("Invoices");
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        // stringWriter字符串是用来保存XML文档的
        StringWriter stringWriter = new StringWriter();
        // xmlWriter是用来把XML文档写入字符串的(工具)
        XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
        // 把创建好的XML文档写入字符串
        try {
            xmlWriter.write(document);
            xmlWriter.close();
        }catch (Exception e){
            logger.info("xmlWriter error");
        }

        HTFKService htfkService=new HTFKService();
        HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
        String result = htfkServiceSoap.payMoney(stringWriter.toString());
        Map<String,Object> mapResult =(Map<String, Object>) JSON.parse(result);

        if(mapResult.get("errorCode")==null || !"0".equals(mapResult.get("errorCode").toString())){
            throw new ServiceException(1,null,"实付款接口接口调用业务系统接口错误_shifukuan");
        }

        //更新事件最新操作时间和完成
        event.setIsPass(1);
        event.setEventStatus(2);
        event.setOperationTime(DateUtil.formatDate(realPayBO.getPayMoneyDate()));
        event.setReadStatus(0);
        eventRepository.updateByPrimaryKeySelective(event);

        //根据事件唯一标识更新合同
        ContractExample example=new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andEventIdEqualTo(event.getId());
        criteria.andIsDeleteEqualTo((byte)0);
        Contract contract=new Contract();
        contract.setPayWay(realPayBO.getPayWay());
        contract.setRealPayMoney(realPayBO.getRealPayMoney());
        contract.setPayMoneyDate(DateUtil.formatDate(realPayBO.getPayMoneyDate()));
        contractRepository.updateByExampleSelective(contract,example);
    }

    /**
     * 调用财务系统建立单据接口方法
     * @param createItemOrderBO
     * @return
     */
    @Override
    public BillRespBO createBill(CreateItemOrderBO createItemOrderBO) {
        try{
            String targetEendPoint=financeWsdlUrl;
            //设置参数
            String xmlStr = this.billXML(createItemOrderBO);
            org.apache.axis.client.Service service=new org.apache.axis.client.Service();
            Call call=(Call) service.createCall();
            call.setOperationName(new QName("http://jiuqi.com.cn","parseStrWithAuth"));
            call.setTargetEndpointAddress(new URL(targetEendPoint));
            call.addParameter("xmlStr", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("userName", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("userPWD", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            String result=(String)call.invoke(new Object[]{xmlStr,"jq","8EE8AD6B3F36473A16E8F2B155D53C9F"});
//            String result = this.test(xmlStr);
            //将xml字符串转为Document对象
            Document document=DocumentHelper.parseText(result);
            //获取根节点
            Element root=document.getRootElement();
            //获取根节点下所有的子元素
            List<Element> elements = root.elements();
            Map<String,Object> map=new HashMap<String,Object>();
            if(elements!=null && elements.size()>0) {
                for (Element element : elements) {
                    map.put(element.getName(),element.getTextTrim());
                }
            }
            //创建BillRespBO对象
            BillRespBO billRespBO=new BillRespBO();
            if(map.get("dataID")!=null){
                billRespBO.setDataID(map.get("dataID").toString());
            }
            if(map.get("billCode")!=null){
                //在billCode不为空的时候进行连接地址拼接
                String url="http://60.29.46.254:8888//login,credentialJQFOEMAIL,00000000000000000000000000000000,"+
                        createItemOrderBO.getSender()+"/showpage,BillNoToolbarPage,GCFKD@"+map.get("billCode").toString()+"@"+createItemOrderBO.getEventCode();
                billRespBO.setBillUrl(url);
            }
            if(map.get("rtnMsg")!=null){
                billRespBO.setRtnMsg(map.get("rtnMsg").toString());
            }
            if(map.get("rtnCode")!=null){
                billRespBO.setRtnCode(Integer.parseInt(map.get("rtnCode").toString()));
            }
            return billRespBO;
        }catch (Exception e){
            logger.info("调用财务系统接口出现错误");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将billBo对象转为xml格式的字符串
     * @param createItemOrderBO
     * @return
     */
    public String billXML(CreateItemOrderBO createItemOrderBO){
        // 创建XML文档树
        Document document = DocumentHelper.createDocument();
        // 创建根节点items
        Element sscinterface = document.addElement("sscinterface");
        sscinterface.addAttribute("dataid",createItemOrderBO.getEventCode());
        sscinterface.addAttribute("operatertype","ADD");
        sscinterface.addAttribute("userid",createItemOrderBO.getSender());
        sscinterface.addAttribute("unitid",createItemOrderBO.getBillReqBO().getUnitid());
        sscinterface.addAttribute("billdefine","GCFKD");
        sscinterface.addAttribute("systemname","FKD");
        Element bill=sscinterface.addElement("bill");
        Element zhubiao = bill.addElement("zhubiao");
        //设置参数
        Element companyName = zhubiao.addElement("companyName");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getCompanyName())){
            companyName.addText(createItemOrderBO.getBillReqBO().getCompanyName());
        }
        Element contractType = zhubiao.addElement("contractType");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getContractType())){
            contractType.addText(createItemOrderBO.getBillReqBO().getContractType());
        }
        Element applyUserName = zhubiao.addElement("applyUserName");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getApplyUserName())){
            applyUserName.addText(createItemOrderBO.getBillReqBO().getApplyUserName());
        }

        Element applyDept = zhubiao.addElement("applyDept");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getApplyDept())){
            applyDept.addText(createItemOrderBO.getBillReqBO().getApplyDept());
        }
        Element clauseItemName = zhubiao.addElement("clauseItemName");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getClauseItemName())){
            clauseItemName.addText(createItemOrderBO.getBillReqBO().getClauseItemName());
        }

        Element itemName = zhubiao.addElement("itemName");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getItemName())){
            itemName.addText(createItemOrderBO.getBillReqBO().getItemName());
        }
        Element contractCode = zhubiao.addElement("contractCode");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getContractCode())){
            contractCode.addText(createItemOrderBO.getBillReqBO().getContractCode());
        }
        Element contractName = zhubiao.addElement("contractName");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getContractName())){
            contractName.addText(createItemOrderBO.getBillReqBO().getContractName());
        }
        Element jiaCompany = zhubiao.addElement("jiaCompany");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getJiaCompany())){
            jiaCompany.addText(createItemOrderBO.getBillReqBO().getJiaCompany());
        }
        Element yiCompany = zhubiao.addElement("yiCompany");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getYiCompany())){
            yiCompany.addText(createItemOrderBO.getBillReqBO().getYiCompany());
        }
        Element bingCompany = zhubiao.addElement("bingCompany");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getBingCompany())){
            bingCompany.addText(createItemOrderBO.getBillReqBO().getBingCompany());
        }
        Element contractMoney = zhubiao.addElement("contractMoney");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getContractMoney())){
            contractMoney.addText(createItemOrderBO.getBillReqBO().getContractMoney());
        }

        Element payMoney = zhubiao.addElement("payMoney");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getPayMoney())){
            payMoney.addText(createItemOrderBO.getBillReqBO().getPayMoney());
        }
        Element totalPayMoney = zhubiao.addElement("totalPayMoney");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getTotalPayMoney())){
            totalPayMoney.addText(createItemOrderBO.getBillReqBO().getTotalPayMoney());
        }
        Element applyMoney = zhubiao.addElement("applyMoney");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getApplyMoney())){
            applyMoney.addText(createItemOrderBO.getBillReqBO().getApplyMoney());
        }
        Element payCause = zhubiao.addElement("payCause");
        if(StringUtils.isNotBlank(createItemOrderBO.getBillReqBO().getPayCause())){
            payCause.addText(createItemOrderBO.getBillReqBO().getPayCause());
        }
        Element eventId = zhubiao.addElement("eventId");
        eventId.addText(createItemOrderBO.getBillReqBO().getEventCode());
        //这里需要设置一个是否是合同类型
        Element isContract = zhubiao.addElement("isContract");
        isContract.addText("true");
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        // stringWriter字符串是用来保存XML文档的
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)
            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
            // 把创建好的XML文档写入字符串
            xmlWriter.write(document);
            logger.info("制单传递参数："+stringWriter.toString());
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    /**
     * 非合同审批通知接口的方法
     *
     * @param approveMsgBO
     */
    private void noContractApprove(ApproveMsgBO approveMsgBO) {
        //根据事件唯一标识eventCode获取事件
        Event event= eventService.getEventByEventCode(approveMsgBO.getEventCode());
        if(event!=null){
            //根据事件唯一标识查询最新未处理事件流
            EventStream eventStream = eventStreamRepository.newestEventStream(event.getId());

            //事件申请人和最新待办事件流待办人相等表明是修改后提交
            if(event.getUserName().equals(eventStream.getUserName())){
                //修改最新待办事件流
                eventStream.setDealCode(1);
                eventStream.setOperationTime(approveMsgBO.getOperationTime());
                eventStream.setApproveResult(approveMsgBO.getApproveResult());
                eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                //设置阅读状态,这里设置相当于设置为已办事件阅读状态
                eventStream.setReadCode(0);
                eventStreamRepository.updateByPrimaryKeySelective(eventStream);

                //新建待办事件流
                Integer eventStreamId = this.createEventStream(approveMsgBO, event.getId());
                //新建消息
                //更改标题为最开始的标题
                approveMsgBO.setEventTitle(event.getTitle());
                this.createMessage(approveMsgBO,eventStreamId);
                //更新事件修改次数和操作时间
                event.setOperationTime(approveMsgBO.getOperationTime());
                event.setUpdateTimes(event.getUpdateTimes()+1);
                eventRepository.updateByPrimaryKeySelective(event);
                return;
            }else{
                //判断工作流状态是不是结束,如果是结束需要更新最新待办事件流审批结果以及意见
                if(approveMsgBO.getWorkflowStatus()==3){
                    //修改最新事件流表
                    eventStream.setDealCode(1);
                    eventStream.setApproveResult(approveMsgBO.getApproveResult());
                    eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                    eventStream.setOperationTime(approveMsgBO.getOperationTime());
                    eventStream.setReadCode(0);
                    eventStreamRepository.updateByPrimaryKeySelective(eventStream);

                    //判断审批意见来设置事件是否是通过
                    if(approveMsgBO.getApproveResult()==1){
                        //通过
                        event.setIsPass(1);
                    }else {
                        //不通过
                        event.setIsPass(-1);
                    }
                    event.setEventStatus(2);
                    event.setOperationTime(approveMsgBO.getOperationTime());
                    eventRepository.updateByPrimaryKeySelective(event);
                    return;
                }

                //其余正常待审批
                eventStream.setOperationTime(approveMsgBO.getOperationTime());
                eventStream.setApproveResult(approveMsgBO.getApproveResult());
                eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                eventStream.setDealCode(1);
                eventStream.setReadCode(0);
                eventStreamRepository.updateByPrimaryKeySelective(eventStream);
                //新建事件流
                Integer eventStreamId = this.createEventStream(approveMsgBO, event.getId());
                //设置标题统一
                approveMsgBO.setEventTitle(event.getTitle());
                this.createMessage(approveMsgBO,eventStreamId);
                event.setOperationTime(approveMsgBO.getOperationTime());
                eventRepository.updateByPrimaryKeySelective(event);
            }
        }else{
            //表明该事件不存在,是新建
            logger.info("事件不存在插入事件表记录、事件流记录、消息表记录【开始】");
            //新建事件
            Event creatEvent = new Event();
            ApproveEventBOConvert.BOToEvent(creatEvent, approveMsgBO);
            //保存事件
            eventRepository.insertSelective(creatEvent);
            //新插入事件流,将新建事件id一起传递过去
            Integer eventStreamId = this.createEventStream(approveMsgBO, creatEvent.getId());
            //新建消息
            this.createMessage(approveMsgBO,eventStreamId);
            logger.info("事件不存在插入事件表记录、事件流记录、消息表记录【结束】");
        }
    }

    /**
     * 合同审批通知接口的方法
     *
     * @param approveMsgBO
     */
    private void contractApprove(ApproveMsgBO approveMsgBO) {
        //根据事件唯一标识查询事件
        Event event=null;
        event = eventService.getEventByEventCode(approveMsgBO.getEventCode());
        if(event!=null){
            //获取最新待办事件流
            EventStream eventStream = eventStreamRepository.newestEventStream(event.getId());
            //判断审批意见是否是通过
            if(approveMsgBO.getApproveResult()==1){
                //通过
                //判断工作流是否是结束状态
                if(approveMsgBO.getWorkflowStatus()==3){
                    //表明结束状态
                    //更新最新待办事件流
                    eventStream.setDealCode(1);
                    eventStream.setApproveResult(approveMsgBO.getApproveResult());
                    eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                    eventStream.setOperationTime(approveMsgBO.getOperationTime());
                    eventStream.setReadCode(0);
                    eventStreamRepository.updateByPrimaryKeySelective(eventStream);

                    //更新事件最新操作时间
                    event.setOperationTime(approveMsgBO.getOperationTime());
                    event.setIsPass(1);
                    eventRepository.updateByPrimaryKeySelective(event);

                    //通知业务系统
                    logger.info("shenpijieguocanshu："+approveMsgBO.getEventCode()+"---"+approveMsgBO.getOperationTime()+"---"+approveMsgBO.getApproveResult()+"---"+approveMsgBO.getResponseDesc());
                    //创建查询条件map对象
                    //----付款结果
                    HTFKService htfkService=new HTFKService();
                    HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
                    String result = htfkServiceSoap.approveResult(approveMsgBO.getEventCode(),null, DateUtil.getDateStr(approveMsgBO.getOperationTime(), "yyyy-MM-dd HH:mm:ss"), approveMsgBO.getApproveResult(), approveMsgBO.getResponseDesc());

                    Map<String, Object> mapResult = (Map<String, Object>) JSON.parse(result);
                    if(mapResult.get("errorCode")==null || !"0".equals(mapResult.get("errorCode").toString())){
                        throw new ServiceException(1,null,"审批结果接口调用业务系统接口错误_shenpijieguo");
                    }
                }else{
                    //是审批中
                    //新建待办事件流
                    Integer eventStreamId = this.createEventStream(approveMsgBO, event.getId());
                    //更新最新待办事件流
                    eventStream.setDealCode(1);
                    eventStream.setApproveResult(approveMsgBO.getApproveResult());
                    eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                    eventStream.setOperationTime(approveMsgBO.getOperationTime());
                    eventStream.setReadCode(0);
                    eventStreamRepository.updateByPrimaryKeySelective(eventStream);
                    //新建消息
                    //这里需要将消息的标题进行设置
                    approveMsgBO.setEventTitle(event.getTitle());
                    this.createMessage(approveMsgBO,eventStreamId);
                    //更新事件最新操作时间
                    event.setOperationTime(approveMsgBO.getOperationTime());
                    eventRepository.updateByPrimaryKeySelective(event);
                }
            }else{
                //不通过
                //更新最新待办事件流
                eventStream.setDealCode(1);
                eventStream.setApproveResult(approveMsgBO.getApproveResult());
                eventStream.setResponseDesc(approveMsgBO.getResponseDesc());
                eventStream.setOperationTime(approveMsgBO.getOperationTime());
                eventStream.setReadCode(0);
                eventStreamRepository.updateByPrimaryKeySelective(eventStream);

                //更新事件最新操作时间
                event.setOperationTime(approveMsgBO.getOperationTime());
//                event.setUpdateTimes(event.getUpdateTimes()+1);
                event.setEventStatus(2);
                event.setReadStatus(0);
                event.setIsPass(-1);
                eventRepository.updateByPrimaryKeySelective(event);

                //通知业务系统
                logger.info("shenpijieguocanshu："+approveMsgBO.getEventCode()+"---"+approveMsgBO.getOperationTime()+"---"+approveMsgBO.getApproveResult()+"---"+approveMsgBO.getResponseDesc());
                //创建查询条件map对象
                //----付款结果
                HTFKService htfkService=new HTFKService();
                HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
                String result = htfkServiceSoap.approveResult(approveMsgBO.getEventCode(),null, DateUtil.getDateStr(approveMsgBO.getOperationTime(), "yyyy-MM-dd HH:mm:ss"), approveMsgBO.getApproveResult(), approveMsgBO.getResponseDesc());

                Map<String, Object> mapResult = (Map<String, Object>) JSON.parse(result);
                if(mapResult.get("errorCode")==null || !"0".equals(mapResult.get("errorCode").toString())){
                    throw new ServiceException(1,null,"审批结果接口调用业务系统接口错误_shenpijieguo");
                }
            }
        }else{
            //表明事件不存在
            //表明该事件不存在,是新建
            logger.info("事件不存在插入事件表记录、事件流记录、消息表记录【开始】");
            //新建事件
            Event creatEvent = new Event();
            ApproveEventBOConvert.BOToEvent(creatEvent, approveMsgBO);
            //保存事件
            eventRepository.insertSelective(creatEvent);
            //新插入事件流,将新建事件id一起传递过去
            Integer eventStreamId = this.createEventStream(approveMsgBO, creatEvent.getId());
            //新建消息
            this.createMessage(approveMsgBO,eventStreamId);
            logger.info("事件不存在插入事件表记录、事件流记录、消息表记录【结束】");
        }
    }

    /**
     * 新建事件流
     *
     * @param approveMsgBO
     * @param evntId
     */
    private Integer createEventStream(ApproveMsgBO approveMsgBO, Integer evntId) {
        //将参数进行转换
        EventStream eventStream = new EventStream();
        ApproveStreamBOConvert.BOToStream(eventStream, approveMsgBO);
        //将事件流关联的新建的事件
        eventStream.setEventId(evntId);
        //保存事件流
        eventStreamRepository.insertSelective(eventStream);
        return eventStream.getId();
    }


    /**
     * 新建消息
     *
     * @param approveMsgBO
     */
    private void createMessage(ApproveMsgBO approveMsgBO,Integer eventStreamId) {
        EventMessageBO eventMessageBO = new EventMessageBO();
        ApproveEventMsgBOConvert.BOToMsg(eventMessageBO, approveMsgBO);
        eventMessageBO.setEventStreamId(eventStreamId);
        try {
            //插入消息记录
            eventMessageService.addMessage(eventMessageBO, approveMsgBO.getReceiver());
        } catch (Exception e) {
            throw new ServiceException(1, null, "消息发送接口调用错误!!!");
        }
    }


    /**
     * 这是测试代码
     * @param xmlStr
     * @return
     */
    public String test(String xmlStr){
        logger.info(xmlStr);
        // 创建XML文档树
        Document document = DocumentHelper.createDocument();
        // 创建根节点items
        Element returninfo = document.addElement("returninfo");
        Element dataID = returninfo.addElement("dataID");
        dataID.addText("00001");
        Element rtnCode = returninfo.addElement("rtnCode");
        rtnCode.addText("10");
        Element rtnMsg = returninfo.addElement("rtnMsg");
        rtnMsg.addText("操作结果为：单据已经执行完成");
        Element billUrl = returninfo.addElement("billUrl");
        billUrl.addText("查看单据连接");
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        // stringWriter字符串是用来保存XML文档的
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)
            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
            // 把创建好的XML文档写入字符串
            xmlWriter.write(document);
            logger.info("制单传递参数："+stringWriter.toString());
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

}
