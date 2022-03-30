package com.itchenyang.listerner;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="username") String userName) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userName, session);
        System.out.println(userName+"【websocket消息】有新的连接，总数为:"+webSockets.size());
    }

    @OnClose
    public void onClose(@PathParam(value="username") String userName) {
        webSockets.remove(this);
        sessionPool.remove(userName);
        System.out.println("【websocket消息】连接断开，总数为:"+webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for(WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public Pair<Boolean,String> sendOneMessage(String userName, String message) {
        System.out.println("【websocket消息】单点消息:"+message);
        Session session = sessionPool.get(userName);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
                return Pair.of(true,"消息通知成功");
            } catch (Exception e) {
                e.printStackTrace();
                return Pair.of(false, "异常");
            }
        }
        return Pair.of(true,userName + "不在线");
    }
}
