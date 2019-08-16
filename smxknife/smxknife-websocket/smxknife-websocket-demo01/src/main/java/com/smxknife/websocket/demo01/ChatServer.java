package com.smxknife.websocket.demo01;

import lombok.extern.java.Log;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author smxknife
 * 2019-08-16
 */
@Log
@ServerEndpoint(value = "/chat/{username}",
		encoders = MessageEncoder.class,
		decoders = MessageDecoder.class
)
public class ChatServer {

	private Session session;
	private static Set<ChatServer> chatServers = new CopyOnWriteArraySet<>();
	private static HashMap<String, String> users = new HashMap<>();

	@OnOpen
	public void onOpen(Session session,
	                   @PathParam("username") String userName) {
		log.info(() -> "open session = " + session.toString());
		this.session = session;
		chatServers.add(this);
		users.put(session.getId(), userName);

		Message message = new Message();
		message.setFrom(userName);
		message.setContent("Connected!");
		broadcast(message);
	}

	private static void broadcast(Message message) {
		chatServers.forEach(server -> {
			synchronized (server) {
				try {
					server.session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@OnMessage
	public void onMessage(Session session, Message message) throws IOException, EncodeException {
		log.info(() -> "message = " + message);
		message.setFrom(users.get(session.getId()));
		broadcast(message);
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		log.info(() -> "close session = " + session.toString() + " ,reason = " + reason);
		chatServers.remove(this);
		Message message = new Message();
		message.setFrom(users.get(session.getId()));
		message.setContent("Disconnected!");
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable th) {
		log.info(() -> "error session = " + session.toString() + " , exception = " + th.getMessage());
	}
}
