package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class ApproveMsgReqDTO {
    private String eventId; //事件唯一标识
    private String systemCode;  //系统标识
    private String isContract;  //是否是合同审批类型
    private String eventTitle;  //事件标题
    private String operationTime;   //操作时间
    private String operator;    //经办人
    private String receiver;    //接收人
    private Integer approveResult;   //审批意见
    private Integer workflowStatus;  //工作流状态
    private String responseDesc;   //反馈描述
    private String approveUrl;  //审批链接地址
    private String applyUrl;    //申请链接地址
}
