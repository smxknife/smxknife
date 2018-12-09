package com.smxknife.nio.chat;

import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/23
 */
public class ChatServer {
	public static void main(String[] args) throws IOException {
		int port = 8081;

		new Thread(new ServerHandlerThread(port)).start();
	}
}
