package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.ApplyReqDTO;
import com.tbjj.portal.core.bo.ApplyBO;

/**
 * Created by czhenzhen on 2017/12/28.
 * 明源申请参数转化类
 */
public class ApplyReqDTOToBO {

    public static ApplyBO dtoTOBO(ApplyReqDTO source,ApplyBO target){
        if (source != null){
            target.setTitle(source.getTitle());
            target.setSender(source.getSender());
            target.setOperationTime(source.getOperationTime());
            target.setEventId(source.getEventId());
            target.setSource(source.getSource());
            target.setUrl(source.getUrl());
        }
         return target;
    }
}
