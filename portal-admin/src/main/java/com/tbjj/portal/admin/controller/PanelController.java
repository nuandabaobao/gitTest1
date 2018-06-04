package com.tbjj.portal.admin.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.admin.converter.PanelConverterDTOBO;
import com.tbjj.portal.admin.dto.BaseResponseDTO;
import com.tbjj.portal.admin.dto.PanelReqDTO;
import com.tbjj.portal.admin.dto.PanelRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.PanelService;
import com.tbjj.portal.core.bo.PanelBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16/016.
 */
@RestController
public class PanelController extends BaseController{

    @Autowired
    private PanelService panelService;

    /**
     * 条件查询面板
     */
    @RequestMapping(value = "/admin/panel/getPanelList",method = RequestMethod.GET)
    public TransResult getPanelList(@RequestParam(required = false) Integer departmentId){
        logger.info("查询所有面板信息【开始】进入com.tbjj.portal.admin.controller.PanelController.getPanelList()");
        TransResult result=new TransResult();

        List<PanelBO> panels = panelService.getPanelList(departmentId);

        PanelRespDTO panelRespDTO=new PanelRespDTO();
        panelRespDTO.success("查询面板信息成功");
        panelRespDTO.setData(panels);

        result.success("查询成功");
        result.setContent(panelRespDTO);
        logger.info("查询所有面板信息完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加面板
     */
    @RequestMapping(value = "/admin/panel/addPanel",method = RequestMethod.POST)
    public TransResult addPanel(@RequestBody PanelReqDTO panelReqDTO, HttpServletRequest request){
        logger.info("添加面板信息【开始】进入com.tbjj.portal.admin.controller.PanelController.addPanel()");
        TransResult result=new TransResult();

        PanelBO panelBO=new PanelBO();
        PanelConverterDTOBO.DTOToBO(panelBO,panelReqDTO);

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            panelBO.setEmployeeId(emp.getId());
        }

        panelService.addPanel(panelBO);

        PanelRespDTO panelRespDTO=new PanelRespDTO();
        panelRespDTO.success("添加面板信息成功");

        result.success("添加成功");
        result.setContent(panelRespDTO);
        logger.info("添加面板信息完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 修改面板
     */
    @RequestMapping(value = "/admin/panel/updatePanel",method = RequestMethod.POST)
    public TransResult updatePanel(@RequestBody PanelReqDTO panelReqDTO,HttpServletRequest request){
        logger.info("修改面板信息【开始】进入com.tbjj.portal.admin.controller.PanelController.updatePanel()");
        TransResult result=new TransResult();

        PanelBO panelBO=new PanelBO();
        PanelConverterDTOBO.DTOToBO(panelBO,panelReqDTO);

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null) {
            panelBO.setEmployeeId(emp.getId());
        }
        panelService.updatePanel(panelBO);

        PanelRespDTO panelRespDTO=new PanelRespDTO();
        panelRespDTO.success("修改面板信息成功");

        result.success("修改成功");
        result.setContent(panelRespDTO);
        logger.info("修改面板信息完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除面板
     */
    @RequestMapping(value = "/admin/panel/updateStatus",method = RequestMethod.GET)
    public TransResult updateStatus(@RequestParam(required = false) Integer id,HttpServletRequest request){
        logger.info("删除面板信息【开始】进入com.tbjj.portal.admin.controller.PanelController.updateStatus()");
        TransResult result=new TransResult();

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        Integer empId=null;
        if(emp!=null){
            empId=emp.getId();
        }
        panelService.updateStatus(id,empId);

        PanelRespDTO panelRespDTO=new PanelRespDTO();
        panelRespDTO.success("删除面板信息成功");

        result.success("删除成功");
        result.setContent(panelRespDTO);
        logger.info("删除面板信息完毕--返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "/panel/addIconPic",method = RequestMethod.POST)
    public TransResult addIconPic(@RequestParam("file") MultipartFile iconPic){
        logger.info("上传图片【开始】进入com.tbjj.portal.admin.controller.PanelController.addIconPic()");
        String url = panelService.addIconPic(iconPic);
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        TransResult transResult = new TransResult();
        baseResponseDTO.success(url);
        transResult.success(baseResponseDTO);
        logger.info("上传图片完毕--返回值：\n"+ JSON.toJSONString(transResult));
        return transResult;
    }
}
