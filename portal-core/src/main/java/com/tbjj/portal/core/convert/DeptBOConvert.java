package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.DeptBO;
import com.tbjj.portal.repository.api.entity.Department;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/13/013.
 */
public class DeptBOConvert {

    public static DeptBO DeptToBO(DeptBO deptBO,Department dept){
        if(dept==null){
            return null;
        }

        deptBO.setId(dept.getId());
        deptBO.setName(dept.getName());
        deptBO.setEmployeeId(dept.getEmployeeId());
        return deptBO;
    }

    public static Department BOToDept(Department dept, DeptBO deptBO){
        if(deptBO.getId()!=null){
            dept.setId(deptBO.getId());
        }
        if(StringUtils.isNotBlank(deptBO.getName())){
            dept.setName(deptBO.getName());
        }
        if(deptBO.getEmployeeId()!=null){
            dept.setEmployeeId(deptBO.getEmployeeId());
        }
        return dept;
    }
}
