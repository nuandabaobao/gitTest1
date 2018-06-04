package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.PanelReqDTO;
import com.tbjj.portal.core.bo.PanelBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/16/016.
 */
public class PanelConverterDTOBO {

    public static PanelBO DTOToBO(PanelBO panelBO, PanelReqDTO panelReqDTO){
        if(panelReqDTO.getId()!=null){
            panelBO.setId(panelReqDTO.getId());
        }
        if(StringUtils.isNotBlank(panelReqDTO.getName())){
            panelBO.setName(panelReqDTO.getName());
        }
        if(panelReqDTO.getDepartmentId()!=null){
            panelBO.setDepartmentId(panelReqDTO.getDepartmentId());
        }
        if(panelReqDTO.getYaxisList()!=null){
            panelBO.setYaxisList(panelReqDTO.getYaxisList());
        }
        if(StringUtils.isNotBlank(panelReqDTO.getIncoUrl())){
            panelBO.setIncoUrl(panelReqDTO.getIncoUrl());
        }
        if(panelReqDTO.getPanelType()!=null){
            panelBO.setPanelType(panelReqDTO.getPanelType());
        }
        return panelBO;
    }
}
