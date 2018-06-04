package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.convert.ApproveMsgReqDTOToBO;
import com.tbjj.portal.api.convert.ApprovePaymentDTOToBO;
import com.tbjj.portal.api.convert.RevMsgDTOToBO;
import com.tbjj.portal.api.dto.ApproveMsgReqDTO;
import com.tbjj.portal.api.dto.ApprovePaymentDTO;
import com.tbjj.portal.api.dto.RevocationMsgReqDTO;
import com.tbjj.portal.common.entity.BackResult;
import com.tbjj.portal.core.FinanceService;
import com.tbjj.portal.core.bo.ApproveMsgBO;
import com.tbjj.portal.core.bo.RealPayBO;
import com.tbjj.portal.core.bo.RevocationBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@RestController
public class FinanceController extends BaseController{

    @Autowired
    private FinanceService financeService;

    /**
     * 通知接口
     * @return
     */
    @RequestMapping(value = "/api/finance/approveMsg",method = RequestMethod.POST)
    public BackResult approveMsg(@RequestBody ApproveMsgReqDTO approveMsgReqDTO){
        logger.info("进入接口通知方法【开始】进入com.tbjj.portal.api.controller.FinanceController.inform()");
        BackResult backResult=new BackResult();
        //进行参数判断如果需要参数为空则进行错误信息返回
        if(StringUtils.isBlank(approveMsgReqDTO.getEventId())){
            backResult.setErrorCode(1001);
            backResult.setMsg("事件唯一标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getSystemCode())){
            backResult.setErrorCode(1001);
            backResult.setMsg("系统标识标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getIsContract())){
            backResult.setErrorCode(1001);
            backResult.setMsg("是否为合同付款类审批标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getEventTitle())){
            backResult.setErrorCode(1001);
            backResult.setMsg("事件标题为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getOperationTime())){
            backResult.setErrorCode(1001);
            backResult.setMsg("操作时间为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getOperator())){
            backResult.setErrorCode(1001);
            backResult.setMsg("经办人为空");
            return backResult;
        }
        if(approveMsgReqDTO.getWorkflowStatus()==null){
            backResult.setErrorCode(1001);
            backResult.setMsg("工作流状态为空");
            return backResult;
        }
        if(StringUtils.isBlank(approveMsgReqDTO.getApplyUrl())){
            backResult.setErrorCode(1001);
            backResult.setMsg("申请链接地址为空");
            return backResult;
        }
        ApproveMsgBO approveMsgBO=new ApproveMsgBO();
        try {
            //调用DTO转BO方法
            ApproveMsgReqDTOToBO.DTOToBO(approveMsgBO,approveMsgReqDTO);
        }catch(Exception e){
            backResult.setErrorCode(1001);
            backResult.setMsg("时间格式转换错误");
            return backResult;
        }
        //调用service层
        financeService.approveMsg(approveMsgBO);
        backResult.success();
        logger.info("通知接口方法【结束】返回参数：\n"+ JSON.toJSONString(backResult));
        return backResult;
    }


    /**
     * 撤销接口
     * @return
     */
    @RequestMapping(value = "/api/finance/revocationMsg",method = RequestMethod.POST)
    public BackResult revocationMsg(@RequestBody RevocationMsgReqDTO revocationMsgReqDTO){
        logger.info("撤销接口【开始】进入com.tbjj.portal.api.controller.FinanceController.revocationMsg()");
        BackResult backResult=new BackResult();
        //进行参数判断
        if(StringUtils.isBlank(revocationMsgReqDTO.getEventId())){
            backResult.setErrorCode(1001);
            backResult.setMsg("事件唯一标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(revocationMsgReqDTO.getSystemCode())){
            backResult.setErrorCode(1001);
            backResult.setMsg("系统标识为空");
            return backResult;
        }
        if(StringUtils.isBlank(revocationMsgReqDTO.getOperationTime())){
            backResult.setErrorCode(1001);
            backResult.setMsg("操作时间为空");
            return  backResult;
        }
        if(StringUtils.isBlank(revocationMsgReqDTO.getIsContract())){
            backResult.setErrorCode(1001);
            backResult.setMsg("是否为合同付款类审批标识为空");
            return  backResult;
        }
        RevocationBO revocationBO =new RevocationBO();
        //将DTO转为BO
        try {
            RevMsgDTOToBO.DTOToBO(revocationBO,revocationMsgReqDTO);
        }catch (Exception e){
            backResult.setErrorCode(1001);
            backResult.setMsg("时间格式不正确");
            return backResult;
        }
        //调用Service层将参数传递下去
        try{
            financeService.revocationMsg(revocationBO);
        }catch (Exception e){
            backResult.setErrorCode(10003);
            backResult.setMsg("服务器异常");
            return backResult;
        }

        backResult.success();
        logger.info("撤销接口【结束】返回参数：\n"+JSON.toJSONString(backResult));
        return backResult;
    }


    /**
     * 实付款合同类型接口
     * @return
     */
    @RequestMapping(value = "/api/finance/approvePayment")
    public BackResult approvePayment(@RequestBody ApprovePaymentDTO approvePaymentDTO){
        logger.info("实付款合同类型接口【开始】进入com.tbjj.portal.api.controller.FinanceController.approvePayment()");
        BackResult backResult=new BackResult();
        //参数判断
        if(StringUtils.isBlank(approvePaymentDTO.getContractCode())){
            backResult.setErrorCode(1001);
            backResult.setMsg("合同编号为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getContractType())){
            backResult.setErrorCode(1001);
            backResult.setMsg("合同类型为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getClauseItemName())){
            backResult.setErrorCode(1001);
            backResult.setMsg("款项名称为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getPayWay())){
            backResult.setErrorCode(1001);
            backResult.setMsg("结算方式为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getRealPayMoney())){
            backResult.setErrorCode(1001);
            backResult.setMsg("实付款金额为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getPayMoneyDate())){
            backResult.setErrorCode(1001);
            backResult.setMsg("付款日期为空");
            return backResult;
        }
        if(StringUtils.isBlank(approvePaymentDTO.getEventId())){
            backResult.setErrorCode(1001);
            backResult.setMsg("事件唯一标识为空");
            return backResult;
        }
        //创建BO对象进行参数转换
        RealPayBO realPayBO =new RealPayBO();
        ApprovePaymentDTOToBO.DTOToBO(realPayBO,approvePaymentDTO);

        //调用Service层进行数据处理
        financeService.approvePayment(realPayBO);


        backResult.success();
        logger.info("实付款合同类型接口【结束】返回参数：\n"+JSON.toJSONString(backResult));
        return backResult;
    }

    @RequestMapping(value = "/api/finance/demo",method = RequestMethod.GET)
    public String demo(String str){
        return "return"+str;
    }
}
