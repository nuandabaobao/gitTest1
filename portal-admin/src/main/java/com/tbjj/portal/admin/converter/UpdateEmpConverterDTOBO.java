package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.EmpReqDTO;
import com.tbjj.portal.core.bo.UpdateEmpBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/13/013.
 */
public class UpdateEmpConverterDTOBO {

    public static UpdateEmpBO DTOToBo(UpdateEmpBO updateEmpBO, EmpReqDTO empReqDTO){
        if(empReqDTO.getId()!=null){
            updateEmpBO.setId(empReqDTO.getId());
        }
        if(StringUtils.isNotBlank(empReqDTO.getUserName())){
            updateEmpBO.setUserName(empReqDTO.getUserName());
        }
        if(StringUtils.isNotBlank(empReqDTO.getName())){
            updateEmpBO.setName(empReqDTO.getName());
        }
        if(StringUtils.isNotBlank(empReqDTO.getMobile())){
            updateEmpBO.setMobile(empReqDTO.getMobile());
        }
        if(empReqDTO.getDepartmentId()!=null){
            updateEmpBO.setDepartmentId(empReqDTO.getDepartmentId());
        }
        return updateEmpBO;
    }
}
