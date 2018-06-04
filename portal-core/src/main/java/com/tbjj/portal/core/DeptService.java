package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.DeptBO;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
public interface DeptService {

    /**
     *查询所有部门列表信息
     */
    List<DeptBO> getDeptList();

    /**
     * 添加部门信息
     */
    void addDept(DeptBO deptBO);

    /**
     * 根据部门id修改部门信息
     */
    void updateDept(DeptBO deptBO);

    /**
     * 修改部门状态信息
     */
    void updateStatusDept(Integer id,Integer employeeId);
}
