package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.AdminLoginDTO;
import com.tbjj.portal.core.bo.AdminLoginBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/18/018.
 */
public class AdminLoginConverter {

    public static AdminLoginBO DTOToBO(AdminLoginBO adminLoginBO, AdminLoginDTO adminLoginDTO){
        if(StringUtils.isNotBlank(adminLoginDTO.getUserName())){
            adminLoginBO.setUserName(adminLoginDTO.getUserName());
        }
        if(StringUtils.isNotBlank(adminLoginDTO.getPassword())){
            adminLoginBO.setPssword(adminLoginDTO.getPassword());
        }
        return adminLoginBO;
    }
}
