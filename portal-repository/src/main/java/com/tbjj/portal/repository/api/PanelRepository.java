package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EmployeeOnPanel;
import com.tbjj.portal.repository.api.entity.Panel;
import com.tbjj.portal.repository.api.entity.PanelExample;

import java.util.List;

public interface PanelRepository extends MybatisBaseRepository<Panel,Integer,PanelExample>{

    /**
     * 查询当前登录用户未绑定面板
     * @param empId
     * @return
     */
    List<EmployeeOnPanel> getPanelNoEmpList(Integer empId);
}