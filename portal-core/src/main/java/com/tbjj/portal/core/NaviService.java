package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.NaviBO;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14/014.
 */
public interface NaviService {

    /**
     *根据部门id查询导航信息
     */
    List<NaviBO> getNaviList(Integer departmentId);

    /**
     * 新增导航信息
     */
    void addNavi(NaviBO naviBO);

    /**
     * 根据id修改导航信息
     */
    void updateNavi(NaviBO naviBO);

    /**
     * 根据导航id修改导航状态信息
     */
    void updateNaviStatus(Integer id,Integer employeeId);
}
