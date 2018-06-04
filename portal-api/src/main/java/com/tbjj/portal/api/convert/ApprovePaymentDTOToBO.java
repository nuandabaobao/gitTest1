package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.ApprovePaymentDTO;
import com.tbjj.portal.core.bo.RealPayBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public class ApprovePaymentDTOToBO {

    public static RealPayBO DTOToBO(RealPayBO realPayBO, ApprovePaymentDTO approvePaymentDTO){
        if(StringUtils.isNotBlank(approvePaymentDTO.getContractCode())){
            realPayBO.setContractCode(approvePaymentDTO.getContractCode());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getContractType())){
            realPayBO.setContractType(approvePaymentDTO.getContractType());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getClauseItemName())){
            realPayBO.setClauseItemName(approvePaymentDTO.getClauseItemName());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getPayWay())){
            realPayBO.setPayWay(approvePaymentDTO.getPayWay());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getRealPayMoney())){
            realPayBO.setRealPayMoney(approvePaymentDTO.getRealPayMoney());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getPayMoneyDate())){
            realPayBO.setPayMoneyDate(approvePaymentDTO.getPayMoneyDate());
        }
        if(StringUtils.isNotBlank(approvePaymentDTO.getEventId())){
            realPayBO.setEventCode(approvePaymentDTO.getEventId());
        }
        return realPayBO;
    }
}
