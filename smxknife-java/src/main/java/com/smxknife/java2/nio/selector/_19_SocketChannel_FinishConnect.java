package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _19_SocketChannel_FinishConnect {

	@Test
	public void finishConnectTest() {

		try(SocketChannel socketChannel = SocketChannel.open()) {
			socketChannel.configureBlocking(false);

			boolean isConnected = socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
			if (!isConnected) {
				System.out.println("连接还没有建立!");
				while (!socketChannel.finishConnect()) {
					System.out.println("    正在建立连接, 请稍后...");
				}
			}

			System.out.println("连接完成!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void serverTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.bind(new InetSocketAddress("localhost", 6666));
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println(socketChannel.getRemoteAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
