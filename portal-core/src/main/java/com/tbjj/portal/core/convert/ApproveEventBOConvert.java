package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.ApproveMsgBO;
import com.tbjj.portal.repository.api.entity.Event;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/28/028.
 */
public class ApproveEventBOConvert {

    public static Event BOToEvent(Event event, ApproveMsgBO approveMsgBO) {
        if(StringUtils.isNotBlank(approveMsgBO.getEventCode())){
            event.setEventCode(approveMsgBO.getEventCode());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getEventTitle())){
            event.setTitle(approveMsgBO.getEventTitle());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getOperator())){
            event.setUserName(approveMsgBO.getOperator());
        }
        if(approveMsgBO.getOperationTime()!=null){
            event.setApplyTime(approveMsgBO.getOperationTime());
            event.setOperationTime(approveMsgBO.getOperationTime());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getSystemCode())){
            event.setSystemCode(approveMsgBO.getSystemCode());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getApplyUrl())){
            event.setApplyUrl(approveMsgBO.getApplyUrl());
        }
        return event;
    }
}
