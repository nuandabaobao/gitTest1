package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.PortalLoginService;
import com.tbjj.portal.core.bo.PortalLoginBO;
import com.tbjj.portal.repository.api.DepartmentRepository;
import com.tbjj.portal.repository.api.EmployeeRepository;
import com.tbjj.portal.repository.api.entity.Employee;
import com.tbjj.portal.repository.api.entity.EmployeeExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@Service
public class PortalLoginServiceImpl implements PortalLoginService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee login(PortalLoginBO portalLoginBO) {
        if(StringUtils.isBlank(portalLoginBO.getUserName())){
            throw new ServiceException(1,null,"用户名未填写");
        }
        if(StringUtils.isBlank(portalLoginBO.getPassword())){
            throw new ServiceException(1,null,"密码未填写");
        }

        EmployeeExample exampleName=new EmployeeExample();
        EmployeeExample.Criteria criteriaName = exampleName.createCriteria();
        criteriaName.andIsDeleteEqualTo((byte)0);
        criteriaName.andUserNameEqualTo(portalLoginBO.getUserName());
        criteriaName.andIsAdminEqualTo(0);
        List<Employee> employeeNames = employeeRepository.selectByExample(exampleName);
        if(employeeNames==null || employeeNames.size()==0){
            throw new ServiceException(2,null,"用户名不存在");
        }

        /*Employee employee = employeeNames.get(0);
        //根据用户名判断其是否是财务人员
        Department department = departmentRepository.selectByPrimaryKey(employee.getDepartmentId());

        if("财务部".equals(department.getName())){
            //使用消息验签进行校验之后再进行设置密码
            criteria.andPasswordEqualTo(portalLoginBO.getPassword());
        }else{
            //普通方式设置密码

        }*/

        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte)0);
        criteria.andUserNameEqualTo(portalLoginBO.getUserName());
        criteria.andPasswordEqualTo(portalLoginBO.getPassword());
        criteria.andIsAdminEqualTo(0);
        List<Employee> employees = employeeRepository.selectByExample(example);
        if(employees==null || employees.size()==0){
            throw new ServiceException(3,null,"密码错误");
        }

        //根据用户名和密码获取用户信息
        Employee emp = employees.get(0);

        //修改用户token
        Employee updateEmp=new Employee();
        updateEmp.setId(emp.getId());
        updateEmp.setToken(UUID.randomUUID().toString().replaceAll("-",""));
        Long expireTime=System.currentTimeMillis()+1800000L   ;
        updateEmp.setExpireTime(expireTime);

        //修改token和expireTime
        employeeRepository.updateByPrimaryKeySelective(updateEmp);

        //设置emp信息
        emp.setToken(updateEmp.getToken());
        emp.setExpireTime(updateEmp.getExpireTime());
        return emp;
    }
}
