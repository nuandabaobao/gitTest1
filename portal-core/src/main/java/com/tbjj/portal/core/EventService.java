package com.tbjj.portal.core;

import com.github.pagehelper.Page;
import com.tbjj.portal.core.bo.EventListCondBO;
import com.tbjj.portal.repository.api.entity.Event;

import java.util.Date;

/**
 * Created by czhenzhen on 2017/12/29.
 */
public interface EventService {

    //根据eventCode获取事件信息
    Event getEventByEventCode(String eventCode);

    //根据事件的主键更改修改的次数
    Integer upateEventTimesById(Integer eventId,Integer upateTimes);

    //根据事件的主键更新最新操作时间
    Integer upateOperateTimeById(Integer eventId, Date operateTime);

    /**
     * 条件查询事件列表
     * @param userName
     * @param eventListCondBO
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page eventService(String userName,EventListCondBO eventListCondBO,Integer currentPage,Integer pageSize);

    /**
     * 事件流未读转为已读
     * @param eventId
     */
    void eventReadStatus(Integer eventId);
}
