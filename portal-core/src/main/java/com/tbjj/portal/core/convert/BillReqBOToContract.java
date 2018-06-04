package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.BillReqBO;
import com.tbjj.portal.repository.api.entity.Contract;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class BillReqBOToContract {
    public static Contract BOToConTract(Contract contract, BillReqBO billReqBO){
        if(StringUtils.isNotBlank(billReqBO.getCompanyName())){
            contract.setCompanyName(billReqBO.getCompanyName());
        }
        if(StringUtils.isNotBlank(billReqBO.getContractType())){
            contract.setContractType(billReqBO.getContractType());
        }
        if(StringUtils.isNotBlank(billReqBO.getApplyUserName())){
            contract.setApplyUserName(billReqBO.getApplyUserName());
        }
        if(StringUtils.isNotBlank(billReqBO.getApplyDept())){
            contract.setApplyDept(billReqBO.getApplyDept());
        }
        if(StringUtils.isNotBlank(billReqBO.getClauseItemName())){
            contract.setClauseitemName(billReqBO.getClauseItemName());
        }
        if(StringUtils.isNotBlank(billReqBO.getItemName())){
            contract.setItemName(billReqBO.getItemName());
        }
        if(StringUtils.isNotBlank(billReqBO.getContractCode())){
            contract.setContractCode(billReqBO.getContractCode());
        }
        if(StringUtils.isNotBlank(billReqBO.getContractName())){
            contract.setContractName(billReqBO.getContractName());
        }
        if(StringUtils.isNotBlank(billReqBO.getJiaCompany())){
            contract.setJiaCompany(billReqBO.getJiaCompany());
        }
        if(StringUtils.isNotBlank(billReqBO.getYiCompany())){
            contract.setYiCompany(billReqBO.getYiCompany());
        }
        if(StringUtils.isNotBlank(billReqBO.getBingCompany())){
            contract.setBingCompany(billReqBO.getBingCompany());
        }
        if(StringUtils.isNotBlank(billReqBO.getContractMoney())){
            contract.setContractMoney(billReqBO.getContractMoney());
        }
        if(StringUtils.isNotBlank(billReqBO.getPayMoney())){
            contract.setPayMoney(billReqBO.getPayMoney());
        }
        if(StringUtils.isNotBlank(billReqBO.getTotalPayMoney())){
            contract.setTotalPayMoney(billReqBO.getTotalPayMoney());
        }
        if(StringUtils.isNotBlank(billReqBO.getApplyMoney())){
            contract.setApplyMoney(billReqBO.getApplyMoney());
        }
        if(StringUtils.isNotBlank(billReqBO.getPayCause())){
            contract.setPayCause(billReqBO.getPayCause());
        }
        if(StringUtils.isNotBlank(billReqBO.getUnitid())){
            contract.setUnitId(billReqBO.getUnitid());
        }
        return contract;
    }
}
