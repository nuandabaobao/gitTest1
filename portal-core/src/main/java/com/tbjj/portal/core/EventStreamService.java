package com.tbjj.portal.core;

import com.tbjj.portal.repository.api.entity.EventStream;

/**
 * Created by czhenzhen on 2017/12/29.
 */
public interface EventStreamService {

    //根据待办事件唯一id修改事件流表
    int updateEventStreamByBacklogId(EventStream eventStream);

    /**
     * 事件流未读转为已读
     * @param eventStreamId
     */
    void eventStreamReadStatus(Integer eventStreamId);
}
