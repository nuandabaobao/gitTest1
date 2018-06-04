package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.NaviEmpListBO;
import com.tbjj.portal.repository.api.entity.EmployeeOnNavi;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/20/020.
 */
public class NaviEmpListConvert {

    public static NaviEmpListBO NaviEmpToBO(NaviEmpListBO naviEmpListBO,EmployeeOnNavi employeeOnNavi){
        if(StringUtils.isNotBlank(employeeOnNavi.getNaviName())){
            naviEmpListBO.setNaviName(employeeOnNavi.getNaviName());
        }
        if(StringUtils.isNotBlank(employeeOnNavi.getNaviUrl())){
            naviEmpListBO.setNaviUrl(employeeOnNavi.getNaviUrl());
        }
        if(employeeOnNavi.getNaviEmpId()!=null){
            naviEmpListBO.setNaviEmpId(employeeOnNavi.getNaviEmpId());
        }
        if(employeeOnNavi.getNaviId()!=null){
            naviEmpListBO.setNaviId(employeeOnNavi.getNaviId());
        }
        return naviEmpListBO;
    }
}
