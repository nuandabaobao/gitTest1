package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.ExcelEmpBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/12/15/015.
 */
public class ExcelToEmpBo {
    private static Logger logger = LoggerFactory.getLogger(ExcelToEmpBo.class);

    public static ExcelEmpBO ArrTOBO(String[] empArr){
        logger.error(Arrays.toString(empArr));
        String userName=empArr[0];
        String password=empArr[1];
        String name=empArr[2];
        String mobile=empArr[3];
        String departmentName=empArr[4];

        ExcelEmpBO excelEmpBO=new ExcelEmpBO();
        excelEmpBO.setUserName(userName);
        excelEmpBO.setName(name);
        excelEmpBO.setPassword(password);
        excelEmpBO.setMobile(mobile);
        excelEmpBO.setDepartmentName(departmentName);

        return excelEmpBO;
    }
}
