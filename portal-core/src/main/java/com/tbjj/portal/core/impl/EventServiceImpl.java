package com.tbjj.portal.core.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.EventService;
import com.tbjj.portal.core.bo.EventListCondBO;
import com.tbjj.portal.repository.api.EventRepository;
import com.tbjj.portal.repository.api.entity.Event;
import com.tbjj.portal.repository.api.entity.EventSearch;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by czhenzhen on 2017/12/29.
 */
@Service
public class EventServiceImpl extends BaseServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    /**
     * 根据事件唯一标识获取事件信息
     * @param eventCode
     * @return
     */
    @Override
    public Event getEventByEventCode(String eventCode) {
        logger.info("根据事件唯一标识获取事件信息,入参:"+eventCode);
        if (StringUtils.isEmpty(eventCode)){
            throw new ServiceException(1,eventCode,"请传入事件唯一标识");
        }
        Event event = eventRepository.getEventByEventCode(eventCode);
//        if (null == event){
//            throw new ServiceException(1002,eventCode,"该条信息还未进行过申请");
//        }
        logger.info("根据事件唯一标识获取事件信息,出参:"+event);
        return event;
    }

    /**
     * 根据事件表主键更新修改次数
     * @param eventId
     * @param upateTimes
     * @return
     */
    @Override
    public Integer upateEventTimesById(Integer eventId, Integer upateTimes) {
        logger.info("更新修改次数，入参："+eventId+","+upateTimes);
        Event event = new Event();
        event.setId(eventId);
        event.setUpdateTimes(upateTimes);
        int count = eventRepository.updateByPrimaryKeySelective(event);
        if (count < 1){
            throw new ServiceException(1002,event,"修改次数更新失败");
        }
        logger.info("更新修改次数，出参:"+count);
        return count;
    }

    /**
     * 根据事件表主键更新最新操作时间
     * @param eventId
     * @param operateTime
     * @return
     */
    @Override
    public Integer upateOperateTimeById(Integer eventId, Date operateTime) {
        logger.info("事件表更新最新操作时间，入参："+eventId+","+operateTime);
        Event event = new Event();
        event.setId(eventId);
        event.setOperationTime(operateTime);
        int count = eventRepository.updateByPrimaryKeySelective(event);
        if (count < 1){
            throw new ServiceException(1002,event,"最新操作时间更新失败");
        }
        logger.info("事件表更新最新操作时间,出参："+count);
        return count;
    }

    @Override
    public Page eventService(String userName,EventListCondBO eventListCondBO,Integer currentPage,Integer pageSize) {
        //在前台未传递查询eventStatus时,将其设置为经办1
        if(eventListCondBO.getEventStatus()==null){
            eventListCondBO.setEventStatus(1);
        }

        //进行条件查询参数封装
        final Map<String,Object> eventMap=new HashMap<>();
        if(StringUtils.isNotBlank(userName)){
            eventMap.put("userName",userName);
        }
        if(eventListCondBO.getDeptId()!=null && eventListCondBO.getDeptId()!=0){
            eventMap.put("deptId",eventListCondBO.getDeptId());
        }
        if(eventListCondBO.getReadStatus()!=null){
            eventMap.put("readStatus",eventListCondBO.getReadStatus());
        }
        if(eventListCondBO.getApplyTimeSatrt()!=null){
            eventMap.put("applyTimeStart",eventListCondBO.getApplyTimeSatrt());
        }
        if(eventListCondBO.getApplyTimeEnd()!=null){
            eventMap.put("applyTimeEnd",eventListCondBO.getApplyTimeEnd());
        }
        if(eventListCondBO.getOpeTimeStart()!=null){
            eventMap.put("opeTimeStart",eventListCondBO.getOpeTimeStart());
        }
        if(eventListCondBO.getOpeTimeEnd()!=null){
            eventMap.put("opeTimeEnd",eventListCondBO.getOpeTimeEnd());
        }
        if(StringUtils.isNotBlank(eventListCondBO.getWord())){
            eventMap.put("word",eventListCondBO.getWord());
        }

        if(eventListCondBO.getEventStatus()==1){
            //经办
            Page<EventSearch> result= PageHelper.startPage(currentPage,pageSize).doSelectPage(new ISelect() {
                @Override
                public void doSelect() {
                    eventRepository.applyEventList(eventMap);
                }
            });
            return result;
        }else if(eventListCondBO.getEventStatus()==2){
            //待办
            Page<EventSearch> result= PageHelper.startPage(currentPage,pageSize).doSelectPage(new ISelect() {
                @Override
                public void doSelect() {
                    eventRepository.backlogEventList(eventMap);
                }
            });
            return result;
        }else if(eventListCondBO.getEventStatus()==3){
            //已办
            Page<EventSearch> result= PageHelper.startPage(currentPage,pageSize).doSelectPage(new ISelect() {
                @Override
                public void doSelect() {
                    eventRepository.alreadyDoEvent(eventMap);
                }
            });
            return  result;
        }else if(eventListCondBO.getEventStatus()==4){
            Page<EventSearch> result= PageHelper.startPage(currentPage,pageSize).doSelectPage(new ISelect() {
                @Override
                public void doSelect() {
                    eventRepository.finishEvent(eventMap);
                }
            });
            return  result;
        }else{
            throw new ServiceException(2,null,"事件查询错误,联系管理员");
        }
    }

    @Override
    public void eventReadStatus(Integer eventId) {
        Event event=new Event();
        event.setId(eventId);
        event.setReadStatus(1);
        int count = eventRepository.updateByPrimaryKeySelective(event);
        if(count<1){
            throw new ServiceException(1,null,"修改事件阅读状态失败");
        }
    }

}
