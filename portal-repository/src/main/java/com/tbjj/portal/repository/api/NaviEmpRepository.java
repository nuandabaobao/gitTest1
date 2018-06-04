package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EmployeeOnNavi;
import com.tbjj.portal.repository.api.entity.NaviEmp;
import com.tbjj.portal.repository.api.entity.NaviEmpExample;

import java.util.List;

public interface NaviEmpRepository extends MybatisBaseRepository<NaviEmp,Integer,NaviEmpExample>{
    /**
     * 查询登录用户绑定的导航信息
     */
    List<EmployeeOnNavi> getIndexNaviList(Integer employeeId);

}