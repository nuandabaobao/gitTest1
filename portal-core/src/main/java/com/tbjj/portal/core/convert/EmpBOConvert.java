package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.UpdateEmpBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/14/014.
 */
public class EmpBOConvert {

    /**
     * BO转实体
     */
    public static Employee BOToEmployee(Employee employee, UpdateEmpBO updateEmpBO){
        if(updateEmpBO.getId()!=null){
            employee.setId(updateEmpBO.getId());
        }
        if(StringUtils.isNotBlank(updateEmpBO.getUserName())){
            employee.setUserName(updateEmpBO.getUserName());
        }
        if(StringUtils.isNotBlank(updateEmpBO.getName())){
            employee.setName(updateEmpBO.getName());
        }
        if(StringUtils.isNotBlank(updateEmpBO.getPassword())){
            employee.setPassword(updateEmpBO.getPassword());
        }
        if(StringUtils.isNotBlank(updateEmpBO.getMobile())){
            employee.setMobile(updateEmpBO.getMobile());
        }
        if(updateEmpBO.getDepartmentId()!=null){
            employee.setDepartmentId(updateEmpBO.getDepartmentId());
        }
        employee.setIsDelete((byte)0);
        return employee;
    }
}
