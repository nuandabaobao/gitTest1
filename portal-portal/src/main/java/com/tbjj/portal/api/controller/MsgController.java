package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.dto.MsgRespDTO;
import com.tbjj.portal.api.dto.MsgUpdateRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.MsgService;
import com.tbjj.portal.core.bo.MsgBO;
import com.tbjj.portal.repository.api.entity.Employee;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15/015.
 */
@RestController
@RequestMapping(value = "/portal/msg")
public class MsgController extends BaseController{

    @Autowired
    private MsgService msgService;

    /**
     * 查询所有消息
     */
    @RequestMapping(value = "getAllMsg",method = RequestMethod.GET)
    public TransResult getAllMsg(HttpServletRequest request){
        logger.info("获取登录员工所有的消息【开始】进入com.tbjj.portal.api.controller.MsgController.getAllMsg()");
        TransResult result=new TransResult();

        //获取当前登录用户
        Employee emp = this.getLoginUserFromSession(request);
        List<MsgBO> list=null;
        if(emp!=null){
            //根据用户名查询消息
            list=msgService.getAllMsg(emp.getUserName());
        }

        //创建返回值对象
        MsgRespDTO msgRespDTO=new MsgRespDTO();
        msgRespDTO.setData(list);
        msgRespDTO.success("查询成功");

        result.success("成功");
        result.setContent(msgRespDTO);

        logger.info("获取登录员工所有的消息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 消息未读转为已读
     */
    @RequestMapping(value = "changeRead",method = RequestMethod.GET)
    public TransResult changeRead(@RequestParam(required = false) Integer msgId,@RequestParam(required = false) Integer eventStreamId){
        logger.info("消息未读转已读【开始】进入com.tbjj.portal.api.controller.MsgController.changeRead()");
        TransResult result=new TransResult();

        msgService.changeRead(msgId,eventStreamId);

        //创建返回值对象
        MsgUpdateRespDTO msgUpdateRespDTO=new MsgUpdateRespDTO();
        msgUpdateRespDTO.success("未读转已读成功");

        result.success("成功");
        result.setContent(msgUpdateRespDTO);

        logger.info("消息未读转已读【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 清空该用户的消息列表
     */
    @RequestMapping(value = "clearMsg",method = RequestMethod.POST)
    public TransResult clearMsg(@RequestBody JSONObject json,HttpServletRequest request){
        logger.info("清空消息【开始】进入com.tbjj.portal.api.controller.MsgController.clearMsg()");
        TransResult result=new TransResult();

        msgService.clearMsg(json.getString("msgIdStr"));

        //创建返回值对象
        MsgUpdateRespDTO msgUpdateRespDTO=new MsgUpdateRespDTO();
        msgUpdateRespDTO.success("清空消息成功");

        result.success("成功");
        result.setContent(msgUpdateRespDTO);

        logger.info("清空消息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询时是否有未读消息
     */
    @RequestMapping(value = "noReadMsg",method = RequestMethod.GET)
    public TransResult noReadMsg(HttpServletRequest request){
        logger.info("查询是否有未读消息【开始】进入com.tbjj.portal.api.controller.MsgController.isReadMsg()");
        TransResult result=new TransResult();
        //获取当前登录用户
        Employee emp = this.getLoginUserFromSession(request);

        List<MsgBO> list=null;
        if(emp!=null){
            //根据用户名查询消息
            list=msgService.getAllNoReadMsg(emp.getUserName());
        }
        //创建返回值对象
        MsgRespDTO msgRespDTO=new MsgRespDTO();
        if(list!=null && list.size()>0){
            msgRespDTO.setData(1);
        }else{
            msgRespDTO.setData(0);
        }
        msgRespDTO.success("查询是否有未读消息成功");

        result.success("成功");
        result.setContent(msgRespDTO);

        logger.info("查询是否有未读消息消息【结束】,返回值：\n"+ JSON.toJSONString(result));
        return result;
    }

}
