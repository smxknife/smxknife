package com.smxknife.netty.v5.fakeasyn;

import com.smxknife.netty.common.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author smxknife
 * 2018/11/9
 */
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

			// TODO 与bio的不同之处
			TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50, 10000);

			while (true) {
				socket = serverSocket.accept();
				// TODO 与bio的不同之处
				executePool.execute(new TimeServerHandler(socket));
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
