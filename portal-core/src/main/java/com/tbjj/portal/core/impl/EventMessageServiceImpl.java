package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.EventMessageService;
import com.tbjj.portal.core.bo.EventMessageBO;
import com.tbjj.portal.core.convert.EventMessageBOConvert;
import com.tbjj.portal.core.socket.SocketFunction;
import com.tbjj.portal.repository.api.EventMessageRepository;
import com.tbjj.portal.repository.api.entity.EventMessage;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by czhenzhen on 2017/12/28.
 */
@Service
public class EventMessageServiceImpl extends BaseServiceImpl implements EventMessageService {


    @Autowired
    private EventMessageRepository eventMessageRepository;
    /**
     * 参数插入消息表
     * @param bo
     * @return
     */
    @Override
    public int addMessage(EventMessageBO bo,String receiveName) throws IOException {
        logger.info("消息表里插入数据，入参："+bo);
        if (StringUtils.isEmpty(receiveName)){
            throw new ServiceException(1,bo,"请传入消息接收人");
        }
        EventMessage entity = new EventMessage();
        entity = EventMessageBOConvert.boToEntity(bo,entity);
        int count = eventMessageRepository.insertSelective(entity);
        if(count<1){
            throw new ServiceException(1,bo,"添加消息失败");
        }
        SocketFunction socketFunction = new SocketFunction();
        socketFunction.sendToReceive(receiveName, JSONObject.fromObject(bo).toString());
        logger.info("消息表插入成功，出参："+count);
        return count;
    }
}
