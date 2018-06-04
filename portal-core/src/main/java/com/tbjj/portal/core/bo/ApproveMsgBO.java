package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class ApproveMsgBO {
    private String eventCode;
    private String systemCode;
    private String isContract;
    private String eventTitle;
    private Date OperationTime;
    private String operator;
    private String receiver;
    private Integer approveResult;
    private Integer workflowStatus;
    private String responseDesc;
    private String approveUrl;
    private String applyUrl;
}
