package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class ContractReqDTO {
    private String companyName;   //公司名称
    private String contractType;  //合同类型
    private String applyUserName; //申请人
    private String applyDept;     //申请部门
    private String clauseItemName;//款项名称
    private String itemName;      //项目名称
    private String contractCode;  //合同编号
    private String contractName;  //合同名称
    private String jiaCompany;    //甲方单位
    private String yiCompany;     //乙方单位
    private String bingCompany;   //丙方单位
    private String contractMoney; //合同金额
    private String payMoney;      //结算金额
    private String totalPayMoney; //累计已付金额
    private String applyMoney;    //申请金额
    private String payCause;      //付款事由
}
