package com.tbjj.portal.admin.controller;

import com.tbjj.portal.admin.converter.AdminLoginConverter;
import com.tbjj.portal.admin.dto.AdminLoginDTO;
import com.tbjj.portal.admin.dto.AdminLoginRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.AdminLoginService;
import com.tbjj.portal.core.bo.AdminLoginBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/18/018.
 */
@RestController
public class AdminLoginController extends BaseController{

    @Autowired
    private AdminLoginService adminLoginService;

    @RequestMapping(value = "/static/login",method = RequestMethod.POST)
    public TransResult login(@RequestBody AdminLoginDTO adminLoginDTO){
        AdminLoginBO adminLoginBO =new AdminLoginBO();
        AdminLoginConverter.DTOToBO(adminLoginBO,adminLoginDTO);

        Employee employee=adminLoginService.login(adminLoginBO);

        //创建返回前台页面的对象
        AdminLoginRespDTO adminLoginRespDTO=new AdminLoginRespDTO();
        adminLoginRespDTO.setToken(employee.getToken());
        adminLoginRespDTO.setUserName(employee.getUserName());

        adminLoginRespDTO.success("登录成功");
        TransResult result=new TransResult();
        result.success(adminLoginRespDTO);
        return result;
    }
}
