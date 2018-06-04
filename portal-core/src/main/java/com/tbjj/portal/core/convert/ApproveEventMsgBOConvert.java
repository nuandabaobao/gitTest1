package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.ApproveMsgBO;
import com.tbjj.portal.core.bo.EventMessageBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/29/029.
 */
public class ApproveEventMsgBOConvert {

    public static EventMessageBO BOToMsg(EventMessageBO eventMessageBO, ApproveMsgBO approveMsgBO) {
        if(StringUtils.isNotBlank(approveMsgBO.getEventTitle())){
            eventMessageBO.setMessTitle(approveMsgBO.getEventTitle());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getApproveUrl())){
            eventMessageBO.setMessUrl(approveMsgBO.getApproveUrl());
        }
        if(approveMsgBO.getOperationTime()!=null){
            eventMessageBO.setMessDate(approveMsgBO.getOperationTime());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getReceiver())){
            eventMessageBO.setUserName(approveMsgBO.getReceiver());
        }
        return eventMessageBO;
    }
}
