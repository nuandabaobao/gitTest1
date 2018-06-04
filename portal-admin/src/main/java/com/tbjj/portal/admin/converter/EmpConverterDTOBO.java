package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.EmpReqDTO;
import com.tbjj.portal.core.bo.EmpBO;
import org.apache.commons.lang3.StringUtils;

/**
 * 员工前台参数转为后台业务参数
 * Created by Administrator on 2017/12/12/012.
 */
public class EmpConverterDTOBO {

    /**
     * 将DTO转为BO
     */
    public static EmpBO DTOToBO(EmpBO empBO,EmpReqDTO empReqDTO){
        if(StringUtils.isNotBlank(empReqDTO.getName())){
            empBO.setName(empReqDTO.getName());
        }
        if(StringUtils.isNotBlank(empReqDTO.getUserName())){
            empBO.setUserName(empReqDTO.getUserName());
        }
        if(StringUtils.isNotBlank(empReqDTO.getMobile())){
            empBO.setMobile(empReqDTO.getMobile());
        }
        if(empReqDTO.getDepartmentId()!=null){
            empBO.setDepartmentId(empReqDTO.getDepartmentId());
        }
       return empBO;
    }

}
