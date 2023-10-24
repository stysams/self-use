package com.stysa.socket.handler;

import com.stysa.socket.handler.PrefixUrlPathHelper;
import com.stysa.socket.handler.ServletWebSocketServerHandler;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.ServletWebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author StysaMS
 */
@Configuration
@EnableWebSocket
public class ServletWebSocketServerConfigurer implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        //if (registry instanceof ServletWebSocketHandlerRegistry) {
        //    //替换UrlPathHelper
        //    ((ServletWebSocketHandlerRegistry) registry)
        //            .setUrlPathHelper(new PrefixUrlPathHelper("/websocket"));
        //}
        registry
                //添加处理器到对应的路径
                .addHandler(new ServletWebSocketServerHandler(), "/websocket/*")
                //添加握手拦截器
                .addInterceptors(new ServletWebSocketHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

    public static class ServletWebSocketHandshakeInterceptor implements HandshakeInterceptor {

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            //握手之前
            //继续握手返回true, 中断握手返回false
            System.out.println("before hand");
            //return false;
            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
            //握手之后
            System.out.println("after hand");
        }
    }
}

