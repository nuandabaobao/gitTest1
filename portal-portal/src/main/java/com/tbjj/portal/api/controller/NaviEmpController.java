package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.dto.NaviEmpRespDTO;
import com.tbjj.portal.api.dto.NaviEmpURespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.NaviEmpService;
import com.tbjj.portal.core.bo.NaviEmpListBO;
import com.tbjj.portal.core.bo.NaviNoEmpListBO;
import com.tbjj.portal.repository.api.entity.Employee;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@RestController
@RequestMapping(value = "/portal/navi")
public class NaviEmpController extends BaseController{

    @Autowired
    private NaviEmpService naviEmpService;

    /**
     * 登陆的员工绑定导航查询
     */
    @RequestMapping(value = "getIndexNaviList",method = RequestMethod.GET)
    public TransResult getIndexNaviList(HttpServletRequest request){
        logger.info("获取登录员工绑定的导航【开始】进入com.tbjj.portal.api.controller.NaviEmpController.getNaviList()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        List<NaviEmpListBO> naviEmpListBOList=null;
        if(emp!=null){
           naviEmpListBOList= naviEmpService.getIndexNaviList(emp.getId());
        }
        NaviEmpRespDTO naviEmpRespDTO=new NaviEmpRespDTO();
        naviEmpRespDTO.setData(naviEmpListBOList);
        naviEmpRespDTO.success("查询成功");

        result.success("成功");
        result.setContent(naviEmpRespDTO);

        logger.info("获取登录员工绑定的导航【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 登录员工未绑定的导航查询
     */
    @RequestMapping(value = "getNaviNoEmpList",method = RequestMethod.GET)
    public TransResult getNaviNoEmpList(HttpServletRequest request){
        logger.info("获取登录员工未绑定的导航【开始】进入com.tbjj.portal.api.controller.NaviEmpController.getNaviNoEmpList()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        List<NaviNoEmpListBO> naviNoEmpListBOList=null;
        if(emp!=null){
            naviNoEmpListBOList=naviEmpService.getNaviNoEmpList(emp.getId());
        }

        NaviEmpRespDTO naviEmpRespDTO=new NaviEmpRespDTO();
        naviEmpRespDTO.setData(naviNoEmpListBOList);
        naviEmpRespDTO.success("查询成功");

        result.success("成功");
        result.setContent(naviEmpRespDTO);

        logger.info("获取登录员工未绑定的导航【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 当前登录用户绑定导航
     */
    @RequestMapping(value = "empAddNavi",method = RequestMethod.GET)
    public TransResult empAddNavi(@RequestParam(required = false) Integer naviId , HttpServletRequest request){
        logger.info("当前登录员工绑定导航【开始】进入com.tbjj.portal.api.controller.NaviEmpController.empAddNavi()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            naviEmpService.empAddNavi(emp.getId(),naviId);
        }

        NaviEmpURespDTO naviEmpURespDTO =new NaviEmpURespDTO();
        naviEmpURespDTO.success("绑定成功");

        result.success("成功");
        result.setContent(naviEmpURespDTO);

        logger.info("当前登录员工绑定导航【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 当前登录用户与导航绑定解绑接口
     */
    @RequestMapping(value = "empDeleteNavi",method = RequestMethod.GET)
    public TransResult empDeleteNavi(@RequestParam(required = false) Integer naviEmpId , HttpServletRequest request){
        logger.info("当前登录员工与导航解绑【开始】进入com.tbjj.portal.api.controller.NaviEmpController.empDeleteNavi()");
        TransResult result=new TransResult();

        naviEmpService.empDeleteNavi(naviEmpId);

        NaviEmpURespDTO naviEmpURespDTO =new NaviEmpURespDTO();
        naviEmpURespDTO.success("解绑成功");

        result.success("成功");
        result.setContent(naviEmpURespDTO);

        logger.info("当前登录员工与导航解绑【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 当前登录用户与导航绑定解绑
     */
    @RequestMapping(value = "empAndNavi",method = RequestMethod.POST)
    public TransResult empAndNavi(@RequestBody JSONObject json , HttpServletRequest request){
        logger.info("当前登录员工与导航绑定解绑【开始】进入com.tbjj.portal.api.controller.NaviEmpController.empAndNavi()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            naviEmpService.empAndNavi(emp.getId(),json.getString("naviIdStr"));
        }


        NaviEmpURespDTO naviEmpURespDTO =new NaviEmpURespDTO();
        naviEmpURespDTO.success("绑定解绑成功");

        result.success("成功");
        result.setContent(naviEmpURespDTO);

        logger.info("当前登录员工与导航绑定解绑【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

}
