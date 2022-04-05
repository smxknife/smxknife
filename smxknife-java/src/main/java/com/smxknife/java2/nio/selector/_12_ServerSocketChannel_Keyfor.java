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
public class _12_ServerSocketChannel_Keyfor {

	@Test
	public void keyForTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); Selector selector = Selector.open()) {
			// serverSocketChannel.bind(new InetSocketAddress("localhost", 6666));
			serverSocketChannel.configureBlocking(false);

			SelectionKey selectionKey1 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("A selectionKey1 = " + selectionKey1.hashCode());
			SelectionKey selectionKey2 = serverSocketChannel.keyFor(selector);
			System.out.println("B selectionKey2 = " + selectionKey2.hashCode());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
