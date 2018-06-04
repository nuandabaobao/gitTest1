package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.MsgBO;
import com.tbjj.portal.repository.api.entity.EventMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/15/015.
 */
public class EventMsgConvert {

    public static MsgBO MsgToBO(MsgBO msgBO, EventMessage eventMessage){
        if(eventMessage.getMessId()!=null){
            msgBO.setMessId(eventMessage.getMessId());
        }
        if(StringUtils.isNotBlank(eventMessage.getMessTitle())){
            msgBO.setMessTitle(eventMessage.getMessTitle());
        }
        if(StringUtils.isNotBlank(eventMessage.getMessUrl())){
            msgBO.setMessUrl(eventMessage.getMessUrl());
        }
        if(eventMessage.getMessDate()!=null){
            msgBO.setMessDate(eventMessage.getMessDate());
        }
        if(StringUtils.isNotBlank(eventMessage.getUserName())){
            msgBO.setUserName(eventMessage.getUserName());
        }
        if(eventMessage.getMessStatus()!=null){
            msgBO.setMessStatus(eventMessage.getMessStatus());
        }
        if(eventMessage.getEventStreamId()!=null){
            msgBO.setEventStreamId(eventMessage.getEventStreamId());
        }
        return msgBO;
    }
}
