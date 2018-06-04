package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.EventMessageBO;

import java.io.IOException;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public interface EventMessageService {

    //消息添加至消息表
    int addMessage(EventMessageBO bo,String receiveName) throws IOException;
}
