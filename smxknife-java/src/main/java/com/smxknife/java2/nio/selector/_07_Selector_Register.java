package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _07_Selector_Register {

	@Test
	public void registerWithNonBlockingTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    Selector selector = Selector.open()) {
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(6666));

			System.out.println(serverSocketChannel.isRegistered());
			SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println(selector);
			System.out.println(selectionKey);
			System.out.println(serverSocketChannel.isRegistered());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registerWithBlockingTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    Selector selector = Selector.open()) {
			serverSocketChannel.configureBlocking(true); // 这里如果写成阻塞模式，那么会抛出IllegalBlockingModeException
			serverSocketChannel.bind(new InetSocketAddress(6666));

			System.out.println(serverSocketChannel.isRegistered());
			SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println(selector);
			System.out.println(selectionKey);
			System.out.println(serverSocketChannel.isRegistered());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
