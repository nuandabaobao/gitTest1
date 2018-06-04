package com.tbjj.portal.core.bo;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class RealPayBO {
    private String contractCode;
    private String contractType;
    private String clauseItemName;
    private String payWay;
    private String realPayMoney;
    private String payMoneyDate;
    private String EventCode;
}
