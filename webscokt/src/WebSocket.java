import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by czhenzhen on 2017/12/26.
 */
@ServerEndpoint("/websocket/{userName}")
public class WebSocket {

    private static int onlineCount = 0;
   // private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();//实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象

    private Session session;
    private String userName;

    @OnOpen
    public void onOpen(@PathParam("userName") String userName,Session session) throws IOException {
        System.out.println("userName>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+userName);
        this.userName = userName;
        this.session = session;
        webSocketSet.add(this);//加入set中
        addOnlineCount();//在线数+1
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() throws IOException {
       // clients.remove(userName);
        webSocketSet.remove(this);
       subOnlineCount();
//        System.out.println("链接已关闭");
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {


//        System.out.println("发消息");
        sendMessageTo(message,userName);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        for (WebSocket item : webSocketSet) {
            if (item.userName.equals(To))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : webSocketSet) {
            item.session.getAsyncRemote().sendText(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

}
