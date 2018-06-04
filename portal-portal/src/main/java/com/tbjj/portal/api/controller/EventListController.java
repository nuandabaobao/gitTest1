package com.tbjj.portal.api.controller;

import com.tbjj.portal.api.converter.EventConverterDTOBO;
import com.tbjj.portal.api.dto.EventListReqDTO;
import com.tbjj.portal.api.dto.PageResponseDTO;
import com.tbjj.portal.api.dto.ReadRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.EventService;
import com.tbjj.portal.core.EventStreamService;
import com.tbjj.portal.core.bo.EventListCondBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/1/4/004.
 */
@RestController
@RequestMapping(value = "/portal/event")
public class EventListController extends BaseController{

    @Autowired
    private EventService eventService;

    @Autowired
    private EventStreamService eventStreamService;

    /**
     * 条件查询事件列表
     * @param eventListReqDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "getEventList",method = RequestMethod.POST)
    public TransResult getEventList(@RequestBody EventListReqDTO eventListReqDTO, HttpServletRequest request,
                                    @RequestParam(value="currentPage",defaultValue = "1",required = false) Integer currentPage,
                                    @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize){
        logger.info("前台列表条件查询【开始】,进入com.tbjj.portal.api.controller.EventListController.getEventList()");
        TransResult result=new TransResult();

        //获取当前登录用户
        Employee emp = this.getLoginUserFromSession(request);
        PageResponseDTO pageResponseDTO=null;
        if(emp!=null){
            EventListCondBO eventListCondBO=new EventListCondBO();
            //将事件请求查询条件DTO转为BO
            EventConverterDTOBO.DTOToBO(eventListCondBO,eventListReqDTO);
            //调用service层进行条件查询
            pageResponseDTO=new PageResponseDTO(eventService.eventService(emp.getUserName(),eventListCondBO,currentPage,pageSize));
        }
        pageResponseDTO.success("条件查询事件成功");
        result.success("成功");
        result.setContent(pageResponseDTO);
        logger.info("前台列表条件查询【结束】");
        return result;
    }

    /**
     * 事件未读转已读
     * @param eventId
     * @return
     */
    @RequestMapping(value ="eventReadStatus",method = RequestMethod.GET)
    public TransResult eventReadStatus(@RequestParam(required = false) Integer eventId){
        logger.info("前台事件由未转为已读【开始】,进入com.tbjj.portal.api.controller.EventListController.eventReadStatus()");
        TransResult result=new TransResult();

        eventService.eventReadStatus(eventId);

        ReadRespDTO readRespDTO=new ReadRespDTO();
        readRespDTO.success("事件未读转已读成功");
        result.success("成功");
        result.setContent(readRespDTO);
        logger.info("前台事件由未读转为已读【结束】");
        return result;
    }


    /**
     * 事件流未读转已读
     * @param eventStreamId
     * @return
     */
    @RequestMapping(value ="eventStreamReadStatus",method = RequestMethod.GET)
    public TransResult eventStreamReadStatus(@RequestParam(required = false) Integer eventStreamId){
        logger.info("前台事件流由未转为已读【开始】,进入com.tbjj.portal.api.controller.EventListController.eventStreamReadStatus()");
        TransResult result=new TransResult();

        eventStreamService.eventStreamReadStatus(eventStreamId);

        ReadRespDTO readRespDTO=new ReadRespDTO();
        readRespDTO.success("事件流未读转已读成功");
        result.success("成功");
        result.setContent(readRespDTO);
        logger.info("前台事件流由未读转为已读【结束】");
        return result;
    }
}
