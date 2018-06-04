package com.tbjj.portal.core.convert;

import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.ApplyBO;
import com.tbjj.portal.repository.api.entity.Event;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public class ApplyBOConvert {
    public static Event boToEntity(ApplyBO source,Event target){
        if (source != null){
            target.setTitle(source.getTitle());//事件标题
            target.setApplyTime(DateUtil.formatDate(source.getOperationTime()));//申请时间
            target.setOperationTime(target.getApplyTime());
            target.setApplyUrl(source.getUrl());//申请的连接地址
            target.setEventCode(source.getEventId());//事件唯一标识
            target.setUserName(source.getSender());//申请人
            target.setSystemCode(source.getSource());//系统标识
        }
        return target;
    }
}
