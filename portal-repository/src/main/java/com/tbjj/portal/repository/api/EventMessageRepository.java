package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EventMessage;
import com.tbjj.portal.repository.api.entity.EventMessageExample;

import java.util.List;

public interface EventMessageRepository extends MybatisBaseRepository<EventMessage,Integer,EventMessageExample>{

    /**
     * 查询所有消息(该事件未完成)
     * @param userName
     * @return
     */
    List<EventMessage> getAllMsg(String userName);

    /**
     * 查询所有未读消息
     * @param userName
     * @return
     */
    List<EventMessage> getAllNoReadMsg(String userName);
}