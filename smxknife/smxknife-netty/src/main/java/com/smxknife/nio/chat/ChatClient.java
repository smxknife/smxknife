package com.smxknife.nio.chat;

import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/23
 */
public class ChatClient {
	public static void main(String[] args) throws IOException {
		int port = 8081;
		String ip = "127.0.0.1";

		new Thread(new ClientEmulatorThread(port, ip)).start();

	}
}
