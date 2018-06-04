package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/10/010.
 */
@Data
public class CreateItemOrderBO {
    private String source;
    private String eventCode;
    private String title;
    private String sender;
    private String receiver;
    private Date operationTime;
    private BillReqBO billReqBO;
}
