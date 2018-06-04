package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class RevocationBO {
    private String eventCode;
    private String systemCode;
    private Date operationTime;
    private String isContract;
}
