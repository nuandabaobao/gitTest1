package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.*;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
public interface PanelEmpService {
    /**
     * 查询当前登录用户绑定的面板
     * @param empId
     * @return
     */
    List<PanelEmpListBO> getPanelEmpList(Integer empId);

    /**
     * 查询当前登录用户未绑定的面板
     * @param empId
     * @return
     */
    List<PanelNoEmpListBO> getPanelNoEmpList(Integer empId);

    /**
     * 用户绑定面板
     * @param empId
     * @param panelId
     */
    void empAddPanel(Integer empId, Integer panelId);

    /**
     * 用户解绑面板
     * @param panelEmpId
     */
    void empDeletePanel(Integer panelEmpId);

    /**
     * 当前登录用户绑定解绑面板
     * @param empId
     * @param panelIdStr
     */
    void panelAndEmp(Integer empId, String panelIdStr);

    /**
     * 根据用户id查询面板数据
     * @param empId
     * @return
     */
    List<PanelShowBO> indexPanelList(Integer empId);

    /**
     * 首页更改年查询报表数据
     * @param panelYearChangeBO
     * @return
     */
    PanelShowBO indexYearChange(PanelYearChangeBO panelYearChangeBO);

    /**
     * 改变时间查询面板数据
     * @param panelTimeChangeBO
     * @return
     */
    PanelChangeShowBO chagePanelTime(PanelTimeChangeBO panelTimeChangeBO);
}
