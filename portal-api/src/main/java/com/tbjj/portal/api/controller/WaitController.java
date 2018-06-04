package com.tbjj.portal.api.controller;

import com.tbjj.portal.api.convert.ApplyReqDTOToBO;
import com.tbjj.portal.api.convert.CreateItemOrderDTOToBO;
import com.tbjj.portal.api.convert.WaitReqDTOToBO;
import com.tbjj.portal.api.convert.WaitToFinishDTOToBO;
import com.tbjj.portal.api.dto.ApplyReqDTO;
import com.tbjj.portal.api.dto.CreateItemOrderDTO;
import com.tbjj.portal.api.dto.WaitReqDTO;
import com.tbjj.portal.api.dto.WaitToFinishDTO;
import com.tbjj.portal.common.entity.BackResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.WaitService;
import com.tbjj.portal.core.bo.ApplyBO;
import com.tbjj.portal.core.bo.CreateItemOrderBO;
import com.tbjj.portal.core.bo.WaitBO;
import com.tbjj.portal.core.bo.WaitToFinishBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by czhenzhen on 2017/12/28.
 */
@RestController
public class WaitController extends BaseController {

    @Autowired
    private WaitService waitService;
    /**
     * 业务系统发起申请的接口
     * @param dto
     * @return
     */
    @RequestMapping(value = "/api/business/applyItem",method = RequestMethod.POST)
    public BackResult addApply(@RequestBody ApplyReqDTO dto){
        logger.info("业务系统发起申请，入参;"+dto);
        BackResult transResult = new BackResult();
        ApplyBO bo = new ApplyBO();
        bo = ApplyReqDTOToBO.dtoTOBO(dto,bo);
        int count = waitService.addApply(bo);
        transResult.success();
       /* transResult.setMessage("申请添加成功");
        transResult.setContent(count);*/
        logger.info("业务系统添加申请成功，出参："+transResult);
        return  transResult;

    }


    /**
     * 业务系统提交待办事项
     * @param dto
     * @return
     */
    @RequestMapping(value = "/api/business/transPendingItem",method = RequestMethod.POST)
    public BackResult addWait(@RequestBody WaitReqDTO dto) throws IOException {
        logger.info("添加待办事项，入参："+dto);
        BackResult backResult = new BackResult();
        WaitBO bo = new WaitBO();
        bo = WaitReqDTOToBO.dtoToBO(dto,bo);
        Integer count = waitService.addWait(bo);
        backResult.success();
        logger.info("添加待办事项成功，出参："+backResult);
        return  backResult;

    }

    /**
     * 业务系统待办转已办接口
     * @param dto
     * @return
     */
    @RequestMapping(value = "/api/business/transDoneItem",method = RequestMethod.POST)
    public BackResult waitToFinish(@RequestBody WaitToFinishDTO dto){
        logger.info("业务系统待办转已办，入参："+dto);
        BackResult backResult = new BackResult();
        WaitToFinishBO bo = new WaitToFinishBO();
        bo = WaitToFinishDTOToBO.dtoToBO(dto,bo);
        waitService.waitToFinish(bo);
        backResult.success();
        logger.info("业务系统待办转已办,出参："+backResult);
        return backResult;
    }

    /**
     * 付款申请制单接口
     * @return
     */
    @RequestMapping(value = "/api/business/createItemOrder",method = RequestMethod.POST)
    public BackResult createItemOrder(@RequestBody CreateItemOrderDTO createItemOrderDTO){
        logger.info("付款申请制单【开始】进入方法：com.tbjj.portal.api.controller.WaitController.createItemOrder()");
        BackResult backResult = new BackResult();

        //首先进行参数判断
        if(StringUtils.isBlank(createItemOrderDTO.getSource())){
            backResult.setErrorCode(1001);
            backResult.setMsg("系统标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(createItemOrderDTO.getEventId())){
            backResult.setErrorCode(1001);
            backResult.setMsg("审批事件唯一标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(createItemOrderDTO.getTitle())){
            backResult.setErrorCode(1001);
            backResult.setMsg("申请事件标题为空");
            return backResult;
        }
        if(StringUtils.isBlank(createItemOrderDTO.getSender())){
            backResult.setErrorCode(1001);
            backResult.setMsg("发起人为空");
            return backResult;
        }
        if(StringUtils.isBlank(createItemOrderDTO.getOperationTime())){
            backResult.setErrorCode(1001);
            backResult.setMsg("操作时间为空");
            return backResult;
        }
        if(createItemOrderDTO.getBusinessParams()==null){
            backResult.setErrorCode(1001);
            backResult.setMsg("业务参数为空");
            return backResult;
        }
        CreateItemOrderBO createItemOrderBO=null;
        try {
            createItemOrderBO=new CreateItemOrderBO();
            CreateItemOrderDTOToBO.DTOToBO(createItemOrderBO,createItemOrderDTO);
        }catch (Exception e){
            backResult.setErrorCode(1001);
            backResult.setMsg("时间格式错误");
            return backResult;
        }

        try {
            waitService.createItemOrder(createItemOrderBO);
        }catch (ServiceException e){
            backResult.setErrorCode(10003);
            backResult.setMsg("服务器异常");
            return backResult;
        }

        backResult.success();
        logger.info("付款申请制单【结束】返回参数"+backResult);
        return backResult;
    }
}
