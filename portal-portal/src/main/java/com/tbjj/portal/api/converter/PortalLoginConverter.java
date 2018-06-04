package com.tbjj.portal.api.converter;

import com.tbjj.portal.api.dto.PortalLoginReqDTO;
import com.tbjj.portal.core.bo.PortalLoginBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/20/020.
 */
public class PortalLoginConverter {

    public static PortalLoginBO DTOToBO(PortalLoginBO portalLoginBO, PortalLoginReqDTO portalLoginReqDTO){
        if(StringUtils.isNotBlank(portalLoginReqDTO.getUserName())){
            portalLoginBO.setUserName(portalLoginReqDTO.getUserName());
        }
        if(StringUtils.isNotBlank(portalLoginReqDTO.getPassword())){
            portalLoginBO.setPassword(portalLoginReqDTO.getPassword());
        }
        /*if(portalLoginReqDTO.getIsFinance()!=null){
            portalLoginBO.setIsFinance(portalLoginReqDTO.getIsFinance());
        }*/
        return portalLoginBO;
    }
}
