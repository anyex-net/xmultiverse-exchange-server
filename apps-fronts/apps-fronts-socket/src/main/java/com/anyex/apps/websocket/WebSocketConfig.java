package com.anyex.apps.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author
 * @Date 2023/8/9 9:22
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig {
    /**
     * 自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//
//        container.setMaxTextMessageBufferSize(512000);
//        container.setMaxBinaryMessageBufferSize(512000);
//        container.setMaxSessionIdleTimeout(5*60 * 1000L);
//        return container;
//    }
}
