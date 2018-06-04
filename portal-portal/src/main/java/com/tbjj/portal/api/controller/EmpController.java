package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.dto.PortalExitRespDTO;
import com.tbjj.portal.api.dto.UpdatePwdReqDTO;
import com.tbjj.portal.api.dto.UpdatePwdRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.EmployeeService;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@RestController
@RequestMapping("/portal/emp")
public class EmpController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    /**
     * 修改密码
     */
    @RequestMapping(value = "/updatePwd",method = RequestMethod.POST)
    public TransResult updatePwd(@RequestBody UpdatePwdReqDTO updatePwdReqDTO, HttpServletRequest request){
        logger.info("前台员工修改密码【开始】,进入com.tbjj.portal.api.controller.EmpController.updatePwd()");
        TransResult result=new TransResult();

        //判断输入密码是否为空
        if(StringUtils.isBlank(updatePwdReqDTO.getOldPassword())){
            throw new ServiceException(1,null,"原密码未输入");
        }
        if(StringUtils.isBlank(updatePwdReqDTO.getNewPassword())){
            throw new ServiceException(1,null,"新密码未输入");
        }

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(!emp.getPassword().equals(updatePwdReqDTO.getOldPassword())){
            throw new ServiceException(2,null,"原密码不正确");
        }

        employeeService.updatePassword(updatePwdReqDTO.getNewPassword(),emp.getId());

        request.getSession().removeAttribute("PORTAL_EMP");

        UpdatePwdRespDTO updatePwdRespDTO=new UpdatePwdRespDTO();
        updatePwdRespDTO.success("修改密码成功,请重新登录");

        result.success("修改成功");
        result.setContent(updatePwdRespDTO);
        logger.info("修改密码完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "exit",method = RequestMethod.GET)
    public TransResult exit(HttpServletRequest request){
        logger.info("退出【开始】进入com.tbjj.portal.api.controller.EmpController.exit()");
        TransResult result=new TransResult();

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        //调用service重新修改tocken的值,这样相当于用户用户便不能根据tocken查询用户信息
        employeeService.exit(emp.getId());

        //退出需要将session清空一下
        request.getSession().removeAttribute("PORTAL_EMP");

        PortalExitRespDTO portalExitRespDTO=new PortalExitRespDTO();
        portalExitRespDTO.success("退出成功");

        result.success("退出成功");
        result.setContent(portalExitRespDTO);
        logger.info("退出完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }
}
