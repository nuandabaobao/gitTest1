package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.NaviNoEmpListBO;
import com.tbjj.portal.repository.api.entity.EmployeeOnNavi;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/20/020.
 */
public class NaviNoEmpListConvert {

    public static NaviNoEmpListBO NaviEmpToBO(NaviNoEmpListBO naviNoEmpListBO,EmployeeOnNavi employeeOnNavi){
        if(employeeOnNavi.getNaviId()!=null) {
            naviNoEmpListBO.setNaviId(employeeOnNavi.getNaviId());
        }
        if(StringUtils.isNotBlank(employeeOnNavi.getNaviName())){
            naviNoEmpListBO.setNaviName(employeeOnNavi.getNaviName());
        }
        if(StringUtils.isNotBlank(employeeOnNavi.getNaviUrl())){
            naviNoEmpListBO.setNaviUrl(employeeOnNavi.getNaviUrl());
        }
        return naviNoEmpListBO;
    }
}
