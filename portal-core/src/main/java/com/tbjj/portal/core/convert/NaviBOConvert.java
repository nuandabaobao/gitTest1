package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.NaviBO;
import com.tbjj.portal.repository.api.entity.Navi;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/14/014.
 */
public class NaviBOConvert {

    public static NaviBO NaviToBO(NaviBO naviBO,Navi navi){
        if(navi.getId()!=null){
            naviBO.setId(navi.getId());
        }
        if(StringUtils.isNotBlank(navi.getName())){
            naviBO.setName(navi.getName());
        }
        if(StringUtils.isNotBlank(navi.getUrl())){
            naviBO.setUrl(navi.getUrl());
        }
        if(navi.getDepartmentId()!=null){
            naviBO.setDepartmentId(navi.getDepartmentId());
        }
        if(navi.getEmployeeId()!=null){
            naviBO.setEmployeeId(navi.getEmployeeId());
        }
        return naviBO;
    }

    public static Navi BOToNavi(Navi navi,NaviBO naviBO){
        if(naviBO.getId()!=null){
            navi.setId(naviBO.getId());
        }
        if(StringUtils.isNotBlank(naviBO.getName())){
            navi.setName(naviBO.getName());
        }
        if(StringUtils.isNotBlank(naviBO.getUrl())){
            navi.setUrl(naviBO.getUrl());
        }
        if(naviBO.getDepartmentId()!=null){
            navi.setDepartmentId(naviBO.getDepartmentId());
        }
        if(naviBO.getEmployeeId()!=null){
            navi.setEmployeeId(naviBO.getEmployeeId());
        }
        return navi;
    }

}
