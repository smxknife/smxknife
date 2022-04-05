package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author smxknife
 * 2020/10/18
 */
public class _21_SocketChannel_DuplicateConsume {

	@Test
	public void client1Test() throws IOException {
		Socket socket = new Socket("localhost", 6666);
		socket.close();
	}

	@Test
	public void client2Test() throws IOException {
		Socket socket = new Socket("localhost", 7777);
		socket.close();
	}

	@Test
	public void serverTest() {
		try(ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
		    ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
		    Selector selector = Selector.open();) {

			serverSocketChannel1.bind(new InetSocketAddress(6666));
			serverSocketChannel1.configureBlocking(false);

			serverSocketChannel2.bind(new InetSocketAddress(7777));
			serverSocketChannel2.configureBlocking(false);

			SelectionKey selectionKey1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
			SelectionKey selectionKey2 = serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

			System.out.println("selectionKey1 interestOps = " + selectionKey1.interestOps() + ", readyOps = " + selectionKey1.readyOps());

			boolean isRun = true;
			while (isRun) {
				int selectedKeyCount = selector.select();
				Set<SelectionKey> keys = selector.keys();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();

				System.out.println("keyCount = " + selectedKeyCount);
				System.out.println("keys = " + keys);
				System.out.println("selectionKeys = " + selectionKeys);

				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();

					ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
					InetSocketAddress localAddress = (InetSocketAddress)channel.getLocalAddress();
					SocketChannel socketChannel = channel.accept();
					if (Objects.isNull(socketChannel)) {
						System.out.println("server port = " + localAddress.getPort() + ", socketChannel is null");
					} else {
						System.out.println("server port = " + localAddress.getPort() + ", socketChannel " + socketChannel.getRemoteAddress());
					}
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
