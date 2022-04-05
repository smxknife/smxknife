package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _04_ServerSocketChannel_Block {

	@Test
	public void blockTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			System.out.println("ServerSocketChannel is blocking ? " + serverSocketChannel.isBlocking());

			serverSocketChannel.bind(new InetSocketAddress(6666));
			System.out.println("before accept " + System.currentTimeMillis());
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("after accept " + System.currentTimeMillis());

			socketChannel.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void noneBlockTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			System.out.println("ServerSocketChannel before setting is blocking ? " + serverSocketChannel.isBlocking());
			serverSocketChannel.configureBlocking(false);
			System.out.println("ServerSocketChannel after setting is blocking ? " + serverSocketChannel.isBlocking());

			serverSocketChannel.bind(new InetSocketAddress(6666));
			System.out.println("before accept " + System.currentTimeMillis());
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("after accept " + System.currentTimeMillis());

			socketChannel.close(); // 这里抛出NullPointerException，说明非异常模式

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
