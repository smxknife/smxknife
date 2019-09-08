package com.smxknife.websocket.springboot.demo1;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
public class WebSocketClientDemo1 {

	private static final String WS_URI = "ws://localhost:8080/demo1?account=1";

	private static ExecutorService service;
	private static WebSocketConnectionManager manager = null;
	static {
		System.out.println(Runtime.getRuntime().availableProcessors());
		service = Executors.newFixedThreadPool(3);
	}

	public static void main(String[] args) throws InterruptedException {

		service.execute(new HeartBeatRunnable());

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

	private static class HeartBeatRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {

				try {

//					Executors.newSingleThreadExecutor().execute(() -> {
//						System.out.println(Thread.currentThread().getName());
//					});
//
					StandardWebSocketClient client = new StandardWebSocketClient();
					manager = new WebSocketConnectionManager(client, new ClientHandler(), WS_URI);
					manager.start();

					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(e.getMessage() + " " + Thread.currentThread().getName());
				}
			}
		}
	}

	private static class ClientHandler extends TextWebSocketHandler {
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println(Thread.currentThread().getName() + " connected...........");
			session.sendMessage(new TextMessage("hello, web socket"));
			super.afterConnectionEstablished(session);
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println(Thread.currentThread().getName() + " closed........");
			service.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + " closing");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message)
				throws Exception {
//			System.out.println("xxxxxxxxx" + Thread.currentThread().getName());
//			System.out.println("receive: " + message.getPayload());
//			super.handleTextMessage(session, message);

			System.out.println("handleTextMessage " + Thread.currentThread().getName());
			service.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("handling " + Thread.currentThread().getName());

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
