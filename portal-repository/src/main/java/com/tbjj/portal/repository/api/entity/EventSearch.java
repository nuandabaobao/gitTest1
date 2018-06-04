package com.tbjj.portal.repository.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/6/006.
 */
@Data
public class EventSearch {
    private Integer eventId;//事件唯一标识
    private Integer eventStreamId;//事件流唯一标识
    private String eventTitle;//事件标题
    private Integer readStatus;//阅读状态,事件/或事件流阅读状态
    private Integer deptId;//部门唯一标识
    private String deptName;//部门名称
    private Integer operatorId;//经办人唯一标识
    private String operatorUserName;//经办人用户名
    private String operatorName;//经办人名字
    private Integer receiverId;//接收人唯一标识
    private String receiverUserName;//接收人用户名
    private String receiverName;//接收人名字
    private Date applyTime;//发起时间
    private Date operationTime;//操作时间
    private Integer eventResult;//事件结果
    private String url;
}
