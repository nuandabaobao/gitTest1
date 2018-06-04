package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/6/006.
 */
@Data
public class EventListCondBO {
    private Integer eventStatus;
    private Integer deptId;
    private Integer readStatus;
    private Date applyTimeSatrt;
    private Date applyTimeEnd;
    private Date opeTimeStart;
    private Date opeTimeEnd;
    private String word;
}
