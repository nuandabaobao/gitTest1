package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class RevocationMsgReqDTO {
    private String systemCode;
    private String eventId;
    private String operationTime;
    private String isContract;
}
