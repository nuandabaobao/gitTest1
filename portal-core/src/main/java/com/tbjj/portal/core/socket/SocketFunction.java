package com.tbjj.portal.core.socket;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public class SocketFunction {

    private Session session;

    private  void start(String userName,String message) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();// 获取WebSocket连接器
        //URLEncoder.encode(userName,"utf-8");
        String uri = "ws://localhost:18086//websocket//websocket//"+userName+"/?";
        try {
            SocketClient socketClient=new SocketClient();
            socketClient.onMessage(message);
            session = container.connectToServer(socketClient, URI.create(uri));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }

    }

    public void sendToReceive(String userName,String message) throws IOException {
        SocketFunction socket = new SocketFunction();
        socket.start(userName,message);//创建socket链接
        socket.session.close();
    }
}
