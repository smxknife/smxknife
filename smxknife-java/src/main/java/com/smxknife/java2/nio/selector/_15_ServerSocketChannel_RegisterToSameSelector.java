package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _15_ServerSocketChannel_RegisterToSameSelector {

	@Test
	public void registerToSameSelectorTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
		    Selector selector1 = Selector.open()) {

			serverSocketChannel.configureBlocking(false);
			serverSocketChannel2.configureBlocking(false);

			SelectionKey selectionKey1 = serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
			SelectionKey selectionKey2 = serverSocketChannel2.register(selector1, SelectionKey.OP_ACCEPT);

			System.out.println(selectionKey1);
			System.out.println(selectionKey2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
