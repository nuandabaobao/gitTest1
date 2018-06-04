package com.tbjj.portal.core.socket;

import javax.websocket.*;
import java.io.IOException;

/**
 * Created by czhenzhen on 2017/12/27.
 */
@ClientEndpoint
public class SocketClient {

    private String message;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            session.getBasicRemote().sendText(message);//发送文本

        } catch (IOException ex) {
        }
    }

    @OnMessage
    public void onMessage(String message) {
        this.message=message;
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(){
        System.out.println("连接关闭");
    }

}
