package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by czhenzhen on 2017/12/28.
 */
@Data
public class WaitReqDTO {
    private String eventId;//申请事件唯一标识
    private String title;//申请事件标题
    private String sender;//发起人，三个系统唯一标识
    private String receiver;//接收人，三个系统唯一标识
    private String operationTime;//操作时间
    private String approveUrl;//待办事项审批地址
    private String backlogId;//待办事件唯一标识

}
