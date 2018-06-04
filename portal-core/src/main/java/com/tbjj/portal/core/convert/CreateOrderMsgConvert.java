package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.CreateItemOrderBO;
import com.tbjj.portal.core.bo.EventMessageBO;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class CreateOrderMsgConvert {

    public static EventMessageBO BOToEventMessage(EventMessageBO eventMessageBO,CreateItemOrderBO createItemOrderBO){
        if(StringUtils.isNotBlank(createItemOrderBO.getTitle())){
            eventMessageBO.setMessTitle(createItemOrderBO.getTitle());
        }
        if(createItemOrderBO.getOperationTime()!=null){
            eventMessageBO.setMessDate(createItemOrderBO.getOperationTime());
        }
        if(StringUtils.isNotBlank(createItemOrderBO.getSender())){
            eventMessageBO.setUserName(createItemOrderBO.getSender());
        }
        return eventMessageBO;
    }
}
