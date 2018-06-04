package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.EventStreamService;
import com.tbjj.portal.repository.api.EventMessageRepository;
import com.tbjj.portal.repository.api.EventStreamRepository;
import com.tbjj.portal.repository.api.entity.EventMessage;
import com.tbjj.portal.repository.api.entity.EventMessageExample;
import com.tbjj.portal.repository.api.entity.EventStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by czhenzhen on 2017/12/29.
 */
@Service
public class EventStreamServiceImpl extends BaseServiceImpl implements EventStreamService {

    @Autowired
    private EventStreamRepository eventStreamRepository;

    @Autowired
    private EventMessageRepository eventMessageRepository;

    /**
     * 根据审批事件唯一id更新事件流表
     * @param eventStream
     * @return
     */
    @Override
    public int updateEventStreamByBacklogId(EventStream eventStream) {
        logger.info("根据审批事件唯一id更新事件流表,入参："+eventStream);
        Integer count = eventStreamRepository.updateStreamByBackLogId(eventStream);
        if (count < 1){
            throw new ServiceException(1001,eventStream,"根据审批事件唯一id更新事件流表失败");
        }
        logger.info("根据审批事件唯一id更新事件流表,出参:"+count);
        return count;
    }

    @Override
    public void eventStreamReadStatus(Integer eventStreamId) {
        EventStream eventStream=new EventStream();
        eventStream.setId(eventStreamId);
        eventStream.setReadCode(1);
        eventStreamRepository.updateByPrimaryKeySelective(eventStream);

        //根据事件流id将消息阅读状态改为已读
        EventMessageExample example=new EventMessageExample();
        EventMessageExample.Criteria criteria = example.createCriteria();
        criteria.andEventStreamIdEqualTo(eventStreamId);
        EventMessage eventMessage=new EventMessage();
        eventMessage.setMessStatus(1);
        eventMessageRepository.updateByExampleSelective(eventMessage,example);
    }
}
