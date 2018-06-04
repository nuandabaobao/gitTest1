package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.AdminLoginService;
import com.tbjj.portal.core.bo.AdminLoginBO;
import com.tbjj.portal.repository.api.EmployeeRepository;
import com.tbjj.portal.repository.api.entity.Employee;
import com.tbjj.portal.repository.api.entity.EmployeeExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/18/018.
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee login(AdminLoginBO adminLoginBO) {
        if(StringUtils.isBlank(adminLoginBO.getUserName())){
            throw new ServiceException(1,null,"用户名为空");
        }
        if(StringUtils.isBlank(adminLoginBO.getPssword())){
            throw new ServiceException(1,null,"密码为空");
        }

        EmployeeExample exampleName=new EmployeeExample();
        EmployeeExample.Criteria criteriaName = exampleName.createCriteria();
        criteriaName.andIsDeleteEqualTo((byte)0);
        criteriaName.andUserNameEqualTo(adminLoginBO.getUserName());
        criteriaName.andIsAdminEqualTo(1);
        List<Employee> employeeNames = employeeRepository.selectByExample(exampleName);
        if(employeeNames==null || employeeNames.size()==0){
            throw new ServiceException(2,null,"管理员账号不存在");
        }

        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte)0);
        criteria.andIsAdminEqualTo(1);
        criteria.andUserNameEqualTo(adminLoginBO.getUserName());
        criteria.andPasswordEqualTo(adminLoginBO.getPssword());
        List<Employee> employees = employeeRepository.selectByExample(example);
        if(employees==null || employees.size()==0){
            throw new ServiceException(3,null,"密码错误");
        }
        Employee emp=employees.get(0);
        //用户名和密码正确
        Employee empUpdate=new Employee();
        empUpdate.setToken(UUID.randomUUID().toString().replaceAll("-",""));
        //设置tocken过期时间为30分钟
        Long expireTime=System.currentTimeMillis();
        expireTime=expireTime+1800000L;
        empUpdate.setExpireTime(expireTime);
        empUpdate.setId(emp.getId());
        //修改数据库中的token标识
        employeeRepository.updateByPrimaryKeySelective(empUpdate);
        emp.setToken(empUpdate.getToken());
        return emp;
    }
}
