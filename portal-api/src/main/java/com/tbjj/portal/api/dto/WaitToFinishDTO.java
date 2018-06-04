package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by czhenzhen on 2017/12/29.
 */
@Data
public class WaitToFinishDTO {
    private String eventId;
    private String operationTime;
    private String backlogId;
}
