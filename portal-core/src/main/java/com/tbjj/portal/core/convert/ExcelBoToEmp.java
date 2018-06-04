package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.ExcelEmpBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/3/22/022.
 */
public class ExcelBoToEmp {

    public static Employee excelBoToEmp(Employee emp, ExcelEmpBO excelEmpBO){

        if(StringUtils.isNotBlank(excelEmpBO.getUserName())){
            emp.setUserName(excelEmpBO.getUserName());
        }
        if(StringUtils.isNotBlank(excelEmpBO.getPassword())){
            emp.setPassword(excelEmpBO.getPassword());
        }
        if(StringUtils.isNotBlank(excelEmpBO.getName())){
            emp.setName(excelEmpBO.getName());
        }
        if(StringUtils.isNotBlank(excelEmpBO.getMobile())){
            emp.setMobile(excelEmpBO.getMobile());
        }
        return emp;
    }
}
