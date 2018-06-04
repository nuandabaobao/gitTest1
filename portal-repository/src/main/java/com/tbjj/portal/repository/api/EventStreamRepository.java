package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EventExample;
import com.tbjj.portal.repository.api.entity.EventStream;

public interface EventStreamRepository extends MybatisBaseRepository<EventStream,Integer,EventExample> {

    /**
     * 根据事件id查询最新事件流信息
     */
    EventStream newestEventStream(Integer eventId);

    //根据审批事件唯一id更新事件流表
    Integer updateStreamByBackLogId(EventStream eventStream);
}