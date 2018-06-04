package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.NaviEmpListBO;
import com.tbjj.portal.core.bo.NaviNoEmpListBO;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20/020.
 */
public interface NaviEmpService {
    /**
     * 获取登录用户绑定的导航信息
     */
    List<NaviEmpListBO> getIndexNaviList(Integer employeeId);

    /**
     * 获取登录用户未绑定的导航的信息
     */
    List<NaviNoEmpListBO> getNaviNoEmpList(Integer employeeId);

    /**
     * 员工绑定导航信息
     */
    void empAddNavi(Integer empId, Integer naviId);

    /**
     * 员工导航解绑
     */
    void empDeleteNavi(Integer naviEmpId);

    /**
     * 当前登录用户与导航绑定解绑
     * @param naviIdStr
     */
    void empAndNavi(Integer empId,String naviIdStr);
}
