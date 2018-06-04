package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by czhenzhen on 2017/12/28.
 * 明源申请的请求参数
 */
@Data
public class ApplyReqDTO {
    private String source;//来源系统标识；001-业务系统，002-财务系统
    private String eventId;//申请事件唯一标识
    private String title;//申请事件标题
    private String sender;//发起人，三个系统唯一标识
    private String operationTime;//操作时间
    private String url;//申请事项地址，可直接查看申请详情

}
