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
public class _14_ServerSocketChannel_RegisterToMultSelector {

	@Test
	public void registerToMultiSelectorTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    Selector selector1 = Selector.open();
		    Selector selector2 = Selector.open()) {

			serverSocketChannel.configureBlocking(false);

			SelectionKey selectionKey1 = serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
			SelectionKey selectionKey2 = serverSocketChannel.register(selector2, SelectionKey.OP_ACCEPT);

			System.out.println(selectionKey1);
			System.out.println(selectionKey2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
