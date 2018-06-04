package com.tbjj.portal.api.controller;

import com.tbjj.portal.api.converter.PortalLoginConverter;
import com.tbjj.portal.api.dto.PortalLoginReqDTO;
import com.tbjj.portal.api.dto.PortalLoginRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.PortalLoginService;
import com.tbjj.portal.core.bo.PortalLoginBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@RestController
public class PortalLoginController {

    @Autowired
    private PortalLoginService portalLoginService;

    @RequestMapping(value = "/static/login",method = RequestMethod.POST)
    public TransResult login(@RequestBody PortalLoginReqDTO portalLoginReqDTO){
        PortalLoginBO portalLoginBO=new PortalLoginBO();
        PortalLoginConverter.DTOToBO(portalLoginBO, portalLoginReqDTO);

        Employee employee=portalLoginService.login(portalLoginBO);

        PortalLoginRespDTO portalLoginRespDTO=new PortalLoginRespDTO();
        portalLoginRespDTO.setToken(employee.getToken());
        portalLoginRespDTO.setUserName(employee.getUserName());
        portalLoginRespDTO.success("登录成功");

        TransResult result=new TransResult();
        result.success("成功");
        result.setContent(portalLoginRespDTO);
        return result;
    }
}
