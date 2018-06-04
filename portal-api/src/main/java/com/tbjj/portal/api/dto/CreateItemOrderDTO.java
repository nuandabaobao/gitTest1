package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class CreateItemOrderDTO {
    private String source;
    private String eventId;
    private String title;
    private String sender;
    private String operationTime;
    private ContractReqDTO businessParams;
}
