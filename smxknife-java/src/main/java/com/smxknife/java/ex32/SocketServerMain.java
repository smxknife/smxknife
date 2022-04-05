package com.smxknife.java.ex32;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author smxknife
 * 2020/9/9
 */
public class SocketServerMain {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888, 1);
		while (true) {
			Socket socket = serverSocket.accept();

//			try (InputStreamReader reader = new InputStreamReader(socket.getInputStream())) {
//				char[] chars = new char[1024];
//				int len = 0;
//				while ((len = reader.read(chars)) != -1) {
//					System.out.println("---- " + new String(chars));
//				}
//			}

			try (InputStream is = socket.getInputStream()) {
				byte[] bytes = new byte[1024];
				int read = 0;
				while ((read = is.read(bytes)) != -1) {
					System.out.println(bytesToHex(bytes));
				}

			}
		}

	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if(hex.length() < 2){
				sb.append(0);
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
