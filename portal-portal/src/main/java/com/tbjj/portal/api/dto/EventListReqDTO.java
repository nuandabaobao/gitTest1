package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/4/004.
 */
@Data
public class EventListReqDTO {
    private Integer eventStatus;
    private Integer deptId;
    private Integer readStatus;
    private String applyTimeStartStr;
    private String applyTimeEndStr;
    private String opeTimeStartStr;
    private String opeTimeEndStr;
    private String word;
}
