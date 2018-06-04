package com.tbjj.portal.api.controller;


import com.tbjj.portal.repository.api.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * 从域对象获取用户信息
 * Created by Administrator on 2017/12/12/012.
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Employee getLoginUserFromSession(javax.servlet.http.HttpServletRequest request){
        return (Employee)request.getSession().getAttribute("LOGIN_EMP");
    }

    protected final Employee getLoginUserFromSession(HttpSession session){
        return (Employee)session.getAttribute("LOGIN_EMP");
    }
}
