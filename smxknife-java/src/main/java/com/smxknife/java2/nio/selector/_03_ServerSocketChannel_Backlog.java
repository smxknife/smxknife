package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _03_ServerSocketChannel_Backlog {

	// backlog测试server端
	@Test
	public void backlogServerTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.bind(new InetSocketAddress("localhost", 6666), 60);

			ServerSocket serverSocket = serverSocketChannel.socket();

			TimeUnit.SECONDS.sleep(20); // 在accept之前阻塞，让client都进入队列

			boolean isRun = true;
			while (isRun) {
				Socket socket = serverSocket.accept();
				socket.close();
			}

			serverSocket.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// backlog测试client端
	@Test
	public void backlogClientTest() throws IOException {
		for (int i = 0; i < 100; i++) {
			Socket socket = new Socket("localhost", 6666);
			socket.close();
			System.out.println("客户端连接个数为：" + i);
		}
	}
}
