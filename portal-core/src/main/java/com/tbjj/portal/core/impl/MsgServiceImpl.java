package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.MsgService;
import com.tbjj.portal.core.bo.MsgBO;
import com.tbjj.portal.core.convert.EventMsgConvert;
import com.tbjj.portal.repository.api.EventMessageRepository;
import com.tbjj.portal.repository.api.EventStreamRepository;
import com.tbjj.portal.repository.api.entity.EventMessage;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15/015.
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private EventMessageRepository eventMessageRepository;

    @Autowired
    private EventStreamRepository eventStreamRepository;

    @Override
    public List<MsgBO> getAllMsg(String userName) {
        if(StringUtils.isBlank(userName)){
            throw new ServiceException(1,null,"错误，前联系管理员");
        }
        List<EventMessage> eventMessages = eventMessageRepository.getAllMsg(userName);
        List<MsgBO> list=new ArrayList<>();
        if(eventMessages!=null && eventMessages.size()>0){
            for (EventMessage eventMessage:eventMessages) {
                MsgBO msgBO=new MsgBO();
                EventMsgConvert.MsgToBO(msgBO,eventMessage);
                list.add(msgBO);
            }
        }
        return list;
    }


    @Override
    public void changeRead(Integer msgId,Integer eventStreamId) {
        if(msgId==null){
            throw new ServiceException(1,null,"消息主键为空");
        }
        EventMessage eventMessage=new EventMessage();
        eventMessage.setMessStatus(1);
        eventMessage.setMessId(msgId);
        eventMessageRepository.updateByPrimaryKeySelective(eventMessage);

        //根据事件流id将事件流未读状态改为已读状态
        EventStream eventStream=new EventStream();
        eventStream.setId(eventStreamId);
        eventStream.setReadCode(1);
        eventStreamRepository.updateByPrimaryKeySelective(eventStream);
    }


    @Override
    public void clearMsg(String msgIdStr) {
        List<Integer> list=new ArrayList<>();
        try{
        msgIdStr=msgIdStr.replace("[", "").replace("]", "").trim();
        if(StringUtils.isNotBlank(msgIdStr)) {
            String[] splits = msgIdStr.split(",");
            for (String idStr:splits) {
                int idInt = Integer.parseInt(idStr);
                list.add(idInt);
            }
        }
        }
        catch (Exception e){
            throw new ServiceException(1,null,"id转换错误");
        }

        if(list!=null && list.size()>0){
            for (Integer msgId:list) {
                EventMessage eventMsg=new EventMessage();
                eventMsg.setIsDelete((byte)1);
                eventMsg.setMessId(msgId);
                eventMessageRepository.updateByPrimaryKeySelective(eventMsg);
            }
        }
    }

    @Override
    public List<MsgBO> getAllNoReadMsg(String userName) {
        if(StringUtils.isBlank(userName)){
            throw new ServiceException(1,null,"错误，前联系管理员");
        }
        List<EventMessage> eventMessages = eventMessageRepository.getAllNoReadMsg(userName);
        List<MsgBO> list=new ArrayList<>();
        if(eventMessages!=null && eventMessages.size()>0){
            for (EventMessage eventMessage:eventMessages) {
                MsgBO msgBO=new MsgBO();
                EventMsgConvert.MsgToBO(msgBO,eventMessage);
                list.add(msgBO);
            }
        }
        return list;
    }
}
