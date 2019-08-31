package com.smxknife.websocket.springboot.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2019/8/27
 */
@Slf4j
public class WebSocketHandlerDemo1 extends TextWebSocketHandler {

	private static final String ATTR = "account";

	// WebSocketSession不同于HttpSession，每次断开连接（正常断开或发生异常断开）都会重新起一个WebSocketSession
	private static final Map<String, WebSocketSession> SOCKET_SESSIONS = new ConcurrentHashMap<>();

	public void sendMsg(String account, String msg) {
		SOCKET_SESSIONS.values().stream()
				.filter(session -> session.getAttributes().get(ATTR).equals(account))
				.filter(Objects::nonNull)
				.filter(WebSocketSession::isOpen)
				.forEach(session -> {
					try {
						session.sendMessage(new TextMessage(msg));
					} catch (IOException e) {
						log.error(e.getMessage(), e);
						e.printStackTrace();
					}
				});
	}

	public HandshakeInterceptor interceptor() {
		return new InnerHandshakeInterceptor();
	}

	private class InnerHandshakeInterceptor implements HandshakeInterceptor {

		/**
		 * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
		 * 通过attributes参数设置WebSocketSession的属性
		 */
		@Override
		public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

			HttpServletRequest httpRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
			String attr = httpRequest.getParameter(ATTR);
			if (StringUtils.isEmpty(attr)) {
				log.error("beforeHandshake return false, attr is not ok");
				return false;
			}
			map.put(ATTR, attr);
			log.info("beforeHandshake handler = {}, map = {}", webSocketHandler, map);
			return true;
		}

		/**
		 * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
		 */
		@Override
		public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
			log.info("afterHandshake handler = {}, response = {}", webSocketHandler, serverHttpResponse);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		SOCKET_SESSIONS.put(session.getId(), session);
		log.info("afterConnectionEstablished session = {}, all sessionMap = {}", session, SOCKET_SESSIONS);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		log.info("handleMessage session = {}, message = {}", session, message.getPayload());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("handleTextMessage session = {}, message = {}", session, message.getPayload());
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
		log.info("handlePongMessage session = {}, message = {}", session, message.getPayload());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.error("handleTransportError session = {}, exception = {}", session, exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		WebSocketSession removed = SOCKET_SESSIONS.remove(session.getId());
		log.info("afterConnectionClosed session = {}, status = {}, all sessionMap = {}", session, status, SOCKET_SESSIONS);
	}

	/**
	 * Whether the WebSocketHandler handles partial messages. If this flag is set to
	 * {@code true} and the underlying WebSocket server supports partial messages,
	 * then a large WebSocket message, or one of an unknown size may be split and
	 * maybe received over multiple calls to
	 * {@link #handleMessage(WebSocketSession, WebSocketMessage)}. The flag
	 * {@link WebSocketMessage#isLast()} indicates if
	 * the message is partial and whether it is the last part.
	 */
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
