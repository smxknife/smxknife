package com.smxknife.network.demo06;

import java.io.IOException;
import java.net.Socket;

/**
 * @author smxknife
 * 2019-02-21
 */
public class SocketDemo {
	public static void main(String[] args) throws IOException {
		try (Socket socket = new Socket("www.smxknife.com", 80)) {
			socket.setSoTimeout(15000);
			System.out.println(socket.toString());
		}

	}
}
