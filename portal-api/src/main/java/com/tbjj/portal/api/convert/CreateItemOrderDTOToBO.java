package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.CreateItemOrderDTO;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.BillReqBO;
import com.tbjj.portal.core.bo.CreateItemOrderBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class CreateItemOrderDTOToBO {

    public static CreateItemOrderBO DTOToBO(CreateItemOrderBO createItemOrderBO,CreateItemOrderDTO createItemOrderDTO){
        if(StringUtils.isNotBlank(createItemOrderDTO.getSource())){
            createItemOrderBO.setSource(createItemOrderDTO.getSource());
        }
        if(StringUtils.isNotBlank(createItemOrderDTO.getEventId())){
            createItemOrderBO.setEventCode(createItemOrderDTO.getEventId());
        }
        if(StringUtils.isNotBlank(createItemOrderDTO.getTitle())){
            createItemOrderBO.setTitle(createItemOrderDTO.getTitle());
        }
        if(StringUtils.isNotBlank(createItemOrderDTO.getSender())){
            createItemOrderBO.setSender(createItemOrderDTO.getSender());
        }
        if(StringUtils.isNotBlank(createItemOrderDTO.getSender())){
            createItemOrderBO.setReceiver(createItemOrderDTO.getSender());
        }
        if(StringUtils.isNotBlank(createItemOrderDTO.getOperationTime())){
            Date date = DateUtil.formatDate(createItemOrderDTO.getOperationTime());
            createItemOrderBO.setOperationTime(date);
        }
        if(createItemOrderDTO.getBusinessParams()!=null){
            BillReqBO billReqBO=new BillReqBO();
            billReqBO.setCompanyName(createItemOrderDTO.getBusinessParams().getCompanyName());
            billReqBO.setContractType(createItemOrderDTO.getBusinessParams().getContractType());
            billReqBO.setApplyUserName(createItemOrderDTO.getBusinessParams().getApplyUserName());
            billReqBO.setApplyDept(createItemOrderDTO.getBusinessParams().getApplyDept());
            billReqBO.setClauseItemName(createItemOrderDTO.getBusinessParams().getClauseItemName());
            billReqBO.setItemName(createItemOrderDTO.getBusinessParams().getItemName());
            billReqBO.setContractCode(createItemOrderDTO.getBusinessParams().getContractCode());
            billReqBO.setContractName(createItemOrderDTO.getBusinessParams().getContractName());
            billReqBO.setJiaCompany(createItemOrderDTO.getBusinessParams().getJiaCompany());
            billReqBO.setYiCompany(createItemOrderDTO.getBusinessParams().getYiCompany());
            billReqBO.setBingCompany(createItemOrderDTO.getBusinessParams().getBingCompany());
            billReqBO.setContractMoney(createItemOrderDTO.getBusinessParams().getContractMoney());
            billReqBO.setPayMoney(createItemOrderDTO.getBusinessParams().getPayMoney());
            billReqBO.setTotalPayMoney(createItemOrderDTO.getBusinessParams().getTotalPayMoney());
            billReqBO.setApplyMoney(createItemOrderDTO.getBusinessParams().getApplyMoney());
            billReqBO.setPayCause(createItemOrderDTO.getBusinessParams().getPayCause());
            billReqBO.setEventCode(createItemOrderDTO.getEventId());
            createItemOrderBO.setBillReqBO(billReqBO);
        }
        return createItemOrderBO;
    }
}
