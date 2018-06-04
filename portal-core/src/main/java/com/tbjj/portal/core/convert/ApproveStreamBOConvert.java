package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.ApproveMsgBO;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/12/28/028.
 */
public class ApproveStreamBOConvert {

    public static EventStream BOToStream(EventStream eventStream, ApproveMsgBO approveMsgBO) {
        if(StringUtils.isNotBlank(approveMsgBO.getSystemCode())){
            eventStream.setSystemCode(approveMsgBO.getSystemCode());
        }
        if(approveMsgBO.getOperationTime()!=null){
            eventStream.setOperationTime(approveMsgBO.getOperationTime());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getReceiver())){
            eventStream.setUserName(approveMsgBO.getReceiver());
        }
        if(StringUtils.isNotBlank(approveMsgBO.getApproveUrl())){
            eventStream.setApproveUrl(approveMsgBO.getApproveUrl());
        }
        return eventStream;
    }
}
