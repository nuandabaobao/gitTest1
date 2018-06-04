package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.RevocationMsgReqDTO;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.RevocationBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public class RevMsgDTOToBO {

    public static RevocationBO DTOToBO(RevocationBO revocationBO, RevocationMsgReqDTO revocationMsgReqDTO){
        if(StringUtils.isNotBlank(revocationMsgReqDTO.getEventId())){
            revocationBO.setEventCode(revocationMsgReqDTO.getEventId());
        }
        if(StringUtils.isNotBlank(revocationMsgReqDTO.getSystemCode())){
            revocationBO.setSystemCode(revocationMsgReqDTO.getSystemCode());
        }
        if(StringUtils.isNotBlank(revocationMsgReqDTO.getOperationTime())){
            Date date = DateUtil.formatDate(revocationMsgReqDTO.getOperationTime());
            revocationBO.setOperationTime(date);
        }
        if(StringUtils.isNotBlank(revocationMsgReqDTO.getIsContract())){
            revocationBO.setIsContract(revocationMsgReqDTO.getIsContract());
        }
        return revocationBO;
    }
}
