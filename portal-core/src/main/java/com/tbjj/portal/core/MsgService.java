package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.MsgBO;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15/015.
 */
public interface MsgService {

    /**
     * 根据登录用户查询所有和该用户有关的消息
     * @param userName
     * @return
     */
    List<MsgBO> getAllMsg(String userName);

    /**
     * 消息未读转已读
     * @param msgId
     */
    void changeRead(Integer msgId,Integer eventStreamId);

    /**
     * 根据消息id清空消息
     * @param msgIdStr
     */
    void clearMsg(String msgIdStr);

    /**
     * 查询所有未读消息
     * @param userName
     * @return
     */
    List<MsgBO> getAllNoReadMsg(String userName);
}
