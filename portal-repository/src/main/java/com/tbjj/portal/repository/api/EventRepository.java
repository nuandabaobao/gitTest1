package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.Event;
import com.tbjj.portal.repository.api.entity.EventExample;
import com.tbjj.portal.repository.api.entity.EventSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EventRepository extends MybatisBaseRepository<Event,Integer,EventExample> {

    //根据事件唯一标识获取事件信息
   Event getEventByEventCode(@Param("eventCode") String eventCode);

    /**
     * 分页条件查询经办事件
     * @param eventMap
     * @return
     */
    List<EventSearch> applyEventList(Map<String, Object> eventMap);

    /**
     * 分页条件查询待办事件
     * @param eventMap
     */
    List<EventSearch> backlogEventList(Map<String, Object> eventMap);

    /**
     * 分页条件查询已办事件
     * @param eventMap
     * @return
     */
    List<EventSearch> alreadyDoEvent(Map<String, Object> eventMap);

    /**
     * 分页条件查询完成事件
     * @param eventMap
     * @return
     */
    List<EventSearch> finishEvent(Map<String, Object> eventMap);
}