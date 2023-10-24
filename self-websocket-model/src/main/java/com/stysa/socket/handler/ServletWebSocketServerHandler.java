package com.stysa.socket.handler;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.web.socket.*;

/**
 * @author StysaMS
 */
public class ServletWebSocketServerHandler implements WebSocketHandler {

    @PostConstruct
    private void init(){
        System.out.println("init ServletWebSocketServerHandler");
    }
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        //连接建立
        System.out.println("link start");
    }

    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message) throws Exception {
        //接收消息
        System.out.println(this);
        System.out.println(session.getUri());
        System.out.print("receive message, id : " + session.getId() + " , message :");
        System.out.println(message.getPayload());
        session.sendMessage(new TextMessage("over!"));
    }

    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) throws Exception {
        //异常处理
        System.out.println("error");
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) throws Exception {
        //连接关闭
        System.out.println("link close");
    }

    @Override
    public boolean supportsPartialMessages() {
        //是否支持接收不完整的消息
        return false;
    }
}