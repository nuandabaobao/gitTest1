package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.Employee;
import com.tbjj.portal.repository.api.entity.EmployeeExample;
import com.tbjj.portal.repository.api.entity.EmployeeSearch;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends MybatisBaseRepository<Employee,Integer,EmployeeExample>{
    /**
     * 分页条件查询用户信息
     */
    List<EmployeeSearch> getEEList(Map<String,Object> employeeMap);
}