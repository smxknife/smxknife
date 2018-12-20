package com.smxknife.netty.v5.bio;

import com.smxknife.netty.common.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("the time server is start in port : " + port);

			Socket socket = null;

			while (true) {
				socket = serverSocket.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				System.out.println("the time server close");
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				serverSocket = null;
			}
		}
	}
}
