package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EmployeeOnPanel;
import com.tbjj.portal.repository.api.entity.PanelData;
import com.tbjj.portal.repository.api.entity.PanelEmp;
import com.tbjj.portal.repository.api.entity.PanelEmpExample;

import java.util.List;
import java.util.Map;

public interface PanelEmpRepository extends MybatisBaseRepository<PanelEmp,Integer,PanelEmpExample>{

    /**
     * 根据用户id查询已关联面板
     * @param empId
     * @return
     */
    List<EmployeeOnPanel> getPanelEmpList(Integer empId);

    /**
     * 根据条件查询提交率
     * @param map
     * @return
     */
    List<PanelData> findSubmit(Map<String,Object> map);

    /**
     * 根据条件查询成功率
     * @param map
     * @return
     */
    List<PanelData> findSuccess(Map<String,Object> map);

    /**
     * 根据条件查询修改率
     * @param map
     * @return
     */
    List<PanelData> findModify(Map<String,Object> map);
}