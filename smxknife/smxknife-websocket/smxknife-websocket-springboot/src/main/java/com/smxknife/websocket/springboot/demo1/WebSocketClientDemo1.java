package com.smxknife.websocket.springboot.demo1;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
public class WebSocketClientDemo1 {

	private static final String WS_URI = "ws://localhost:8080/demo1?account=1";

	public static void main(String[] args) throws InterruptedException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketConnectionManager manager = null;
		manager = new WebSocketConnectionManager(client, new ClientHandler(), WS_URI);
		manager.start();
		System.out.println(manager.getOrigin());
		manager.getHeaders().entrySet().forEach(entry -> {
			entry.getValue().forEach(val -> {
				System.out.println(entry.getKey() + " | " + val);
			});
		});
		manager.getSubProtocols().forEach(p -> {
			System.out.println(p);
		});

		TimeUnit.HOURS.sleep(2);
	}

	private static class ClientHandler extends TextWebSocketHandler {
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("connected...........");
			session.sendMessage(new TextMessage("hello, web socket"));
			super.afterConnectionEstablished(session);
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message)
				throws Exception {
			System.out.println("receive: " + message.getPayload());
			super.handleTextMessage(session, message);
		}
	}
}
