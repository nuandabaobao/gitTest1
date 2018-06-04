package com.tbjj.portal.admin.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.admin.converter.UpdateNaviDTOToBO;
import com.tbjj.portal.admin.dto.NaviReqDTO;
import com.tbjj.portal.admin.dto.NaviRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.NaviService;
import com.tbjj.portal.core.bo.NaviBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14/014.
 */
@RestController
@RequestMapping("/admin/navi")
public class NaviController extends BaseController{

    @Autowired
    private NaviService naviService;

    @RequestMapping(value = "getNaviList",method = RequestMethod.GET)
    public TransResult getNaviList(@RequestParam(value = "departmentId",required = false) Integer departmentId){
        logger.info("查询所有导航信息【开始】进入com.tbjj.portal.admin.controller.NaviController.getNaviList()");
        TransResult result=new TransResult();

        NaviRespDTO naviRespDTO=new NaviRespDTO();
        List<NaviBO> naviBOs=naviService.getNaviList(departmentId);

        naviRespDTO.success("查询成功");
        naviRespDTO.setData(naviBOs);

        result.success("查询成功");
        result.setContent(naviRespDTO);
        logger.info("查询所有导航信息完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "addNavi",method = RequestMethod.POST)
    public TransResult addNavi(@RequestBody NaviReqDTO naviReqDTO, HttpServletRequest request){
        logger.info("添加导航信息【开始】进入com.tbjj.portal.admin.controller.NaviController.addNavi()");
        TransResult result=new TransResult();

        NaviBO naviBO=new NaviBO();
        UpdateNaviDTOToBO.DTOToBO(naviBO,naviReqDTO);

        //获取当前登陆用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            naviBO.setEmployeeId(emp.getId());
        }
        naviService.addNavi(naviBO);

        NaviRespDTO naviRespDTO=new NaviRespDTO();
        naviRespDTO.success("添加成功");
        result.success("添加成功");
        result.setContent(naviRespDTO);
        logger.info("添加导航信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "updateNavi",method = RequestMethod.POST)
    public TransResult updateNavi(@RequestBody NaviReqDTO naviReqDTO,HttpServletRequest request){
        logger.info("修改导航信息【开始】进入com.tbjj.portal.admin.controller.NaviController.updateNavi()");
        TransResult result=new TransResult();

        NaviBO naviBO=new NaviBO();
        UpdateNaviDTOToBO.DTOToBO(naviBO,naviReqDTO);
        //获取当前登陆用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            naviBO.setEmployeeId(emp.getId());
        }
        naviService.updateNavi(naviBO);

        NaviRespDTO naviRespDTO=new NaviRespDTO();
        naviRespDTO.success("修改成功");
        result.success("修改成功");
        result.setContent(naviRespDTO);
        logger.info("修改导航信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "updateNaviStatus",method = RequestMethod.GET)
    public TransResult updateNaviStatus(@RequestParam(value = "id",required = false) Integer id,HttpServletRequest request){
        logger.info("删除导航信息【开始】进入com.tbjj.portal.admin.controller.NaviController.updateNaviStatus()");
        TransResult result=new TransResult();

        //获取当前登陆用户信息
        Employee emp = this.getLoginUserFromSession(request);
        Integer empId=null;
        if(emp!=null){
            empId=emp.getId();
        }
        naviService.updateNaviStatus(id,empId);

        NaviRespDTO naviRespDTO=new NaviRespDTO();
        naviRespDTO.success("删除成功");
        result.success("删除成功");
        result.setContent(naviRespDTO);
        logger.info("删除导航信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

}
