package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.CreateItemOrderBO;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class CreateOrderStreamConvert {

    public static EventStream BOToEventStream(EventStream eventStream, CreateItemOrderBO createItemOrderBO){
        if(StringUtils.isNotBlank(createItemOrderBO.getSource())){
            eventStream.setSystemCode(createItemOrderBO.getSource());
        }
        if(createItemOrderBO.getOperationTime()!=null){
            eventStream.setOperationTime(createItemOrderBO.getOperationTime());
        }
        if(StringUtils.isNotBlank(createItemOrderBO.getReceiver())){
            eventStream.setUserName(createItemOrderBO.getReceiver());
        }
        return eventStream;
    }
}
