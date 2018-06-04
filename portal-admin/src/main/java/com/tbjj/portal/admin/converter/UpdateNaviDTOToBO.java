package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.NaviReqDTO;
import com.tbjj.portal.core.bo.NaviBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/14/014.
 */
public class UpdateNaviDTOToBO {

    public static NaviBO DTOToBO(NaviBO naviBO, NaviReqDTO naviReqDTO){
        if(StringUtils.isNotBlank(naviReqDTO.getName())){
            naviBO.setName(naviReqDTO.getName());
        }
        if(StringUtils.isNotBlank(naviReqDTO.getUrl())){
            naviBO.setUrl(naviReqDTO.getUrl());
        }
        if(naviReqDTO.getDepartmentId()!=null){
            naviBO.setDepartmentId(naviReqDTO.getDepartmentId());
        }
        if(naviReqDTO.getId()!=null){
            naviBO.setId(naviReqDTO.getId());
        }
        return naviBO;
    }
}

