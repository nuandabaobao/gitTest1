package com.tbjj.portal.filter;

import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.common.utils.JsonUtil;
import com.tbjj.portal.core.EmployeeService;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/18/018.
 */
@WebFilter(urlPatterns = "/admin/*",filterName = "loginFilter")
public class AdminLoginFilter implements Filter{

    private static final Logger logger= Logger.getLogger(AdminLoginFilter.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        TransResult result=new TransResult();
        //获取token
        String token = request.getHeader("Authorization");

        if(StringUtils.isBlank(token)){
            result.failure("错误，请联系管理员");
            result.setCode(40001);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Pragma","No-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "accept, content-type,Authorization");
            response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
            response.getWriter().write(JsonUtil.bean2Json(result));
            return;
        }

        //通过token取出用户信息
        Employee emp=employeeService.getEmpByToken(token);

        if(emp==null){
            result.failure("你还没有登录，请先登录");
            result.setCode(40002);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Pragma","No-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "accept, content-type,Authorization");
            response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
            response.getWriter().write(JsonUtil.bean2Json(result));
            return;
        }

        if(emp.getExpireTime()==null || emp.getExpireTime()<System.currentTimeMillis()){
            result.failure("登录信息已过期,请重新登录");
            result.setCode(40003);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Pragma","No-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "accept, content-type,Authorization");
            response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
            response.getWriter().write(JsonUtil.bean2Json(result));
            return;
        }

        //通过session获取employee
        Employee employee=(Employee)request.getSession().getAttribute("LOGIN_EMP");
        if(employee==null || emp.getId()!=employee.getId()){
            //向session放入数据
            request.getSession().setAttribute("LOGIN_EMP",emp);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
