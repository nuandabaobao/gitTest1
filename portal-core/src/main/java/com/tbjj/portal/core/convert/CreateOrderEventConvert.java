package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.CreateItemOrderBO;
import com.tbjj.portal.repository.api.entity.Event;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class CreateOrderEventConvert {

    public static Event BOToEvent(Event event, CreateItemOrderBO createItemOrderBO){
        if(StringUtils.isNotBlank(createItemOrderBO.getSource())){
            event.setSystemCode(createItemOrderBO.getSource());
        }
        if(StringUtils.isNotBlank(createItemOrderBO.getEventCode())){
            event.setEventCode(createItemOrderBO.getEventCode());
        }
        if(StringUtils.isNotBlank(createItemOrderBO.getTitle())){
            event.setTitle(createItemOrderBO.getTitle());
        }
        if(StringUtils.isNotBlank(createItemOrderBO.getSender())){
            event.setUserName(createItemOrderBO.getSender());
        }
        if(createItemOrderBO.getOperationTime()!=null){
            event.setApplyTime(createItemOrderBO.getOperationTime());
            event.setOperationTime(createItemOrderBO.getOperationTime());
        }
        return event;
    }
}
