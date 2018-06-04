package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by czhenzhen on 2017/12/29.
 */
@Data
public class WaitToFinishBO {
    private String eventId;
    private Date operationTime;
    private String backlogId;
    private String isArchive;//是否归档
}
