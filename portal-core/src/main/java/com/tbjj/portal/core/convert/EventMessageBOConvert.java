package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.EventMessageBO;
import com.tbjj.portal.repository.api.entity.EventMessage;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public class EventMessageBOConvert {

    public static EventMessage boToEntity(EventMessageBO source,EventMessage target){
        if(source != null){
            target.setUserName(source.getUserName());
            target.setMessDate(source.getMessDate());
            target.setMessId(source.getMessId());
            target.setMessStatus(source.getMessStatus());
            target.setMessTitle(source.getMessTitle());
            target.setMessUrl(source.getMessUrl());
            target.setEventStreamId(source.getEventStreamId());
        }
        return target;
    }

    private static EventMessageBO entityToBO(EventMessage source,EventMessageBO target){
        if(source != null){
            target.setUserName(source.getUserName());
            target.setMessDate(source.getMessDate());
            target.setMessId(source.getMessId());
            target.setMessStatus(source.getMessStatus());
            target.setMessTitle(source.getMessTitle());
            target.setMessUrl(source.getMessUrl());
        }
        return target;
    }

}
