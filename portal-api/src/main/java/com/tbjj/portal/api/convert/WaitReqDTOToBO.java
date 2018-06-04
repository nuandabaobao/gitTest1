package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.WaitReqDTO;
import com.tbjj.portal.core.bo.WaitBO;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public class WaitReqDTOToBO {
    //明源待办请求dto转bo
    public static WaitBO dtoToBO(WaitReqDTO resource,WaitBO target){
        if (resource != null){
            target.setApproveUrl(resource.getApproveUrl());
            target.setBacklogId(resource.getBacklogId());
            target.setEventId(resource.getEventId());
            target.setOperationTime(resource.getOperationTime());
            target.setRevceier(resource.getReceiver());
            target.setSender(resource.getSender());
            target.setTitle(resource.getTitle());
        }
        return target;
    }
}
