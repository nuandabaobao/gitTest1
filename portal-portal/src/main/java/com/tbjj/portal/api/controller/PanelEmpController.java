package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.converter.ChangeTimeConverter;
import com.tbjj.portal.api.converter.PanelYearConverter;
import com.tbjj.portal.api.dto.*;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.PanelEmpService;
import com.tbjj.portal.core.bo.*;
import com.tbjj.portal.repository.api.entity.Employee;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
@RestController
@RequestMapping(value = "/portal/panel")
public class PanelEmpController extends BaseController{

    @Autowired
    private PanelEmpService panelEmpService;

    /**
     * 查询登录用户绑定的面板
     * @param request
     * @return
     */
    @RequestMapping(value = "getPanelEmpList",method = RequestMethod.GET)
    public TransResult getPanelEmpList(HttpServletRequest request){
        logger.info("获取登录员工绑定的面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.getPanelEmpList()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        List<PanelEmpListBO> panelEmpListBOList=null;
        if(emp!=null){
            //根据用户id查询面板
            panelEmpListBOList=panelEmpService.getPanelEmpList(emp.getId());
        }

        PanelEmpRespDTO panelEmpRespDTO=new PanelEmpRespDTO();
        panelEmpRespDTO.setData(panelEmpListBOList);
        panelEmpRespDTO.success("查询成功");

        result.success("成功");
        result.setContent(panelEmpRespDTO);

        logger.info("获取登录员工绑定的面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 获取当前登录用户未绑定的面板
     * @param request
     * @return
     */
    @RequestMapping(value = "getPanelNoEmpList",method = RequestMethod.GET)
    public TransResult getPanelNoEmpList(HttpServletRequest request){
        logger.info("获取登录员工未绑定的面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.getPanelNoEmpList()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        List<PanelNoEmpListBO> PanelNoEmpListBOList=null;
        if(emp!=null){
            //根据用户id查询面板
            PanelNoEmpListBOList=panelEmpService.getPanelNoEmpList(emp.getId());
        }

        PanelEmpRespDTO panelEmpRespDTO=new PanelEmpRespDTO();
        panelEmpRespDTO.setData(PanelNoEmpListBOList);
        panelEmpRespDTO.success("查询成功");

        result.success("成功");
        result.setContent(panelEmpRespDTO);

        logger.info("获取登录员工未绑定的面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 当前登录用户绑定面板
     * @param panelId
     * @param request
     * @return
     */
    @RequestMapping(value ="empAddPanel",method = RequestMethod.GET)
    public TransResult empAddPanel(@RequestParam(required = false) Integer panelId,HttpServletRequest request){
        logger.info("登录员工绑定面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.empAddPanel()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            //用户绑定面板
            panelEmpService.empAddPanel(emp.getId(),panelId);
        }
        PanelEmpURespDTO panelEmpURespDTO=new PanelEmpURespDTO();
        panelEmpURespDTO.success("绑定面板成功");

        result.success("成功");
        result.setContent(panelEmpURespDTO);

        logger.info("登录员工绑定面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 当前登录用户解绑面板
     * @param request
     * @return
     */
    @RequestMapping(value = "empDeletePanel",method = RequestMethod.GET)
    public TransResult empDeletePanel(@RequestParam(required = false) Integer panelEmpId,HttpServletRequest request){
        logger.info("登录员工解绑面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.empDeletePanel()");
        TransResult result=new TransResult();

        //用户解绑面板
        panelEmpService.empDeletePanel(panelEmpId);

        PanelEmpURespDTO panelEmpURespDTO=new PanelEmpURespDTO();
        panelEmpURespDTO.success("解绑面板成功");

        result.success("成功");
        result.setContent(panelEmpURespDTO);

        logger.info("登录员工解绑面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }


    /**
     * 当前登录用户绑定解绑面板
     * @param json
     * @param request
     * @return
     */
    @RequestMapping(value = "panelAndEmp",method = RequestMethod.POST)
    public TransResult panelAndEmp(@RequestBody JSONObject json,HttpServletRequest request){
        logger.info("登录员工绑定解绑面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.panelAndEmp()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        if(emp!=null){
            //用户解绑面板
            panelEmpService.panelAndEmp(emp.getId(),json.getString("panelIdStr"));
        }
        PanelEmpURespDTO panelEmpURespDTO=new PanelEmpURespDTO();
        panelEmpURespDTO.success("绑定解绑面板成功");

        result.success("成功");
        result.setContent(panelEmpURespDTO);

        logger.info("登录员工绑定解绑面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 根据登录用户首页面板展示
     * @param request
     * @return
     */
    @RequestMapping(value ="indexPanelList",method = RequestMethod.GET)
    public TransResult indexPanelList(HttpServletRequest request){
        logger.info("登录员工首页面板展示【开始】进入com.tbjj.portal.api.controller.PanelEmpController.indexPanelList()");
        TransResult result=new TransResult();

        //获取当前登录用户id
        Employee emp = this.getLoginUserFromSession(request);
        List<PanelShowBO> panelShowBOs =null;
        if(emp!=null){
            //用户面板首页展示
            panelShowBOs =panelEmpService.indexPanelList(emp.getId());
        }
        IndexPanelDTO indexPanelDTO=new IndexPanelDTO();
        indexPanelDTO.setData(panelShowBOs);
        indexPanelDTO.success("首页面板查询成功");

        result.success("成功");
        result.setContent(indexPanelDTO);
        logger.info("登录员工首页面板展示【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 首页改变年展示接口
     * @param indexChangesDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "indexYearChange",method = RequestMethod.POST)
    public TransResult indexYearChange(@RequestBody IndexChangesDTO indexChangesDTO,HttpServletRequest request){
        logger.info("登录员工首页面板更改年【开始】进入com.tbjj.portal.api.controller.PanelEmpController.indexYearChange()");
        TransResult result=new TransResult();

        PanelYearChangeBO panelYearChangeBO=new PanelYearChangeBO();
        PanelYearConverter.DTOToBO(panelYearChangeBO,indexChangesDTO);
        //获取当前登录用户
        Employee emp = this.getLoginUserFromSession(request);
        PanelShowBO panelShowBO=null;
        if(emp!=null) {
            panelYearChangeBO.setEmpId(emp.getId());
            panelYearChangeBO.setUserName(emp.getUserName());
            //调用service层进行条件查询返回对象
            panelShowBO = panelEmpService.indexYearChange(panelYearChangeBO);
        }

        IndexPanelDTO indexPanelDTO=new IndexPanelDTO();
        indexPanelDTO.setData(panelShowBO);
        indexPanelDTO.success("首页面板更改年查询成功");

        result.success("成功");
        result.setContent(indexPanelDTO);
        logger.info("登录员工首页面板更改年【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 进入面板之后改变时间接口
     * @param changeTimeDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "chagePanelTime",method = RequestMethod.POST)
    public TransResult chagePanelTime(@RequestBody ChangeTimeDTO changeTimeDTO, HttpServletRequest request){
        logger.info("登录员工首页面板更改时间【开始】进入com.tbjj.portal.api.controller.PanelEmpController.chagePanelTime()");
        TransResult result=new TransResult();

        PanelTimeChangeBO panelTimeChangeBO=new PanelTimeChangeBO();
        ChangeTimeConverter.DTOToBO(panelTimeChangeBO,changeTimeDTO);
        //获取当前登录用户

        Employee emp = this.getLoginUserFromSession(request);
        PanelChangeShowBO panelChangeShowBO=null;
        if(emp!=null) {
            panelTimeChangeBO.setEmpId(emp.getId());
            panelTimeChangeBO.setUserName(emp.getUserName());
            //调用service层进行条件查询返回对象
            panelChangeShowBO = panelEmpService.chagePanelTime(panelTimeChangeBO);
        }

        IndexPanelDTO indexPanelDTO=new IndexPanelDTO();
        indexPanelDTO.setData(panelChangeShowBO);
        indexPanelDTO.success("首页面板更改时间查询成功");

        result.success("成功");
        result.setContent(indexPanelDTO);
        logger.info("登录员工首页面板更改时间【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 点击进入面板接口
     * @param panelId
     * @param request
     * @return
     */
    @RequestMapping(value = "intoPanel",method = RequestMethod.GET)
    public TransResult intoPanel(@RequestParam Integer panelId, HttpServletRequest request){
        logger.info("登录员工首页进入面板【开始】进入com.tbjj.portal.api.controller.PanelEmpController.IntoPanel()");
        TransResult result=new TransResult();

        PanelTimeChangeBO panelTimeChangeBO=new PanelTimeChangeBO();
        //设置开始事件和结束时间是当前年
        Date date = new Date();
        Date startDate = DateUtil.getFirstDayOfYear(date);
//        DateUtil.getLastDayOfYear(date);
        Date endDate = date;
        panelTimeChangeBO.setStartTime(startDate);
        panelTimeChangeBO.setEndTime(endDate);
        panelTimeChangeBO.setPanelId(panelId);
        panelTimeChangeBO.setUnit(1);

        //获取当前登录用户
        Employee emp = this.getLoginUserFromSession(request);
        PanelChangeShowBO panelChangeShowBO=null;
        if(emp!=null) {
            panelTimeChangeBO.setEmpId(emp.getId());
            panelTimeChangeBO.setUserName(emp.getUserName());
            //调用service层进行条件查询返回对象
            panelChangeShowBO = panelEmpService.chagePanelTime(panelTimeChangeBO);
        }

        IndexPanelDTO indexPanelDTO=new IndexPanelDTO();
        indexPanelDTO.setData(panelChangeShowBO);
        indexPanelDTO.success("首页进入面板查询成功");

        result.success("成功");
        result.setContent(indexPanelDTO);
        logger.info("登录员工首页进入面板【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }
}
