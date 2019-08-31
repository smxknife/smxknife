package com.smxknife.websocket.springboot.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author smxknife
 * 2019/8/27
 */
@Configuration
@EnableWebSocket
@Component
public class WebSocketConfigDemo1 implements WebSocketConfigurer {

	/**
	 * 注册websocket处理类
	 * @param webSocketHandlerRegistry
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
		//支持websocket 的 connection，指定WebSocketHandlerDemo01处理路径为/demo01 的长连接请求
		WebSocketHandlerDemo1 handlerDemo01 = handler();
		webSocketHandlerRegistry.addHandler(handlerDemo01, "/demo1")
				//添加WebSocket握手请求的拦截器
				.addInterceptors(handlerDemo01.interceptor());

		WebSocketHandlerDemo1 handlerDemo012 = handler();
		webSocketHandlerRegistry.addHandler(handlerDemo012, "/sockjs/demo1")
				.addInterceptors(handlerDemo012.interceptor()).withSockJS();
	}

	@Bean
	public WebSocketHandlerDemo1 handler() {
		return new WebSocketHandlerDemo1();
	}
}
