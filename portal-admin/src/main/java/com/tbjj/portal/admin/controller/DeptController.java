package com.tbjj.portal.admin.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.admin.converter.DeptConverterDTOBO;
import com.tbjj.portal.admin.dto.DeptReqDTO;
import com.tbjj.portal.admin.dto.DeptRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.DeptService;
import com.tbjj.portal.core.bo.DeptBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台部门管理
 * Created by Administrator on 2017/12/13/013.
 */
@RestController
@RequestMapping("/admin/department")
public class DeptController extends BaseController{

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "getdeptList",method = RequestMethod.GET)
    public TransResult getdeptList(){
        logger.info("获取部门信息列表【开始】进入com.tbjj.portal.admin.controller.DeptController.getdeptList()");

        TransResult result=new TransResult();

        List<DeptBO> deptBOs = deptService.getDeptList();

        DeptRespDTO deptRespDTO =new DeptRespDTO();
        deptRespDTO.setData(deptBOs);
        deptRespDTO.success();

        result.setContent(deptRespDTO);
        result.success();

        logger.info("获取部门列表【结束】,返回值：\n"+ JSON.toJSONString(result));

        return result;
    }

    @RequestMapping(value = "addDept",method = RequestMethod.POST)
    public TransResult addDept(@RequestBody DeptReqDTO deptReqDTO, HttpServletRequest request){
        logger.info("添加部门信息【开始】进入com.tbjj.portal.admin.controller.DeptController.addDept()");

        TransResult result=new TransResult();

        DeptBO deptBO=new DeptBO();
        DeptConverterDTOBO.DTOToBO(deptBO,deptReqDTO);
        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            deptBO.setEmployeeId(emp.getId());
        }
        deptService.addDept(deptBO);


        DeptRespDTO deptRespDTO =new DeptRespDTO();
        deptRespDTO.success("添加部门信息成功");
        result.setContent(deptRespDTO);
        result.success();

        logger.info("添加部门信息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "updateDept",method = RequestMethod.POST)
    public TransResult updateDept(@RequestBody DeptReqDTO deptReqDTO,HttpServletRequest request){
        logger.info("修改部门信息【开始】进入com.tbjj.portal.admin.controller.DeptController.updateDept()");

        TransResult result=new TransResult();

        DeptBO deptBO=new DeptBO();
        DeptConverterDTOBO.DTOToBO(deptBO,deptReqDTO);
        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null) {
            deptBO.setEmployeeId(emp.getId());
        }

        deptService.updateDept(deptBO);

        DeptRespDTO deptRespDTO =new DeptRespDTO();
        deptRespDTO.success("修改部门信息成功");
        result.setContent(deptRespDTO);
        result.success();

        logger.info("修改部门信息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    @RequestMapping(value = "updateStatusDept",method = RequestMethod.GET)
    public TransResult updateStatusDept(@RequestParam(value = "id",required = false) Integer id,HttpServletRequest request){
        logger.info("删除部门信息【开始】进入com.tbjj.portal.admin.controller.DeptController.updateStatusDept()");

        TransResult result=new TransResult();

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        Integer empId=null;
        if(emp!=null){
            empId=emp.getId();
        }
        deptService.updateStatusDept(id,empId);

        DeptRespDTO deptRespDTO =new DeptRespDTO();
        deptRespDTO.success("删除部门信息成功");
        result.setContent(deptRespDTO);
        result.success();

        logger.info("删除部门信息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }
}
