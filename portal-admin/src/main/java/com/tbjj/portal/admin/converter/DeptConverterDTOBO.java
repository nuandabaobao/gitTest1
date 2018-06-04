package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.DeptReqDTO;
import com.tbjj.portal.core.bo.DeptBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/14/014.
 */
public class DeptConverterDTOBO {

    public static DeptBO DTOToBO(DeptBO deptBO, DeptReqDTO deptReqDTO) {
        if(deptReqDTO!=null){
            deptBO.setId(deptReqDTO.getId());
        }
        if(StringUtils.isNotBlank(deptReqDTO.getName())){
            deptBO.setName(deptReqDTO.getName());
        }
        return deptBO;
    }
}
