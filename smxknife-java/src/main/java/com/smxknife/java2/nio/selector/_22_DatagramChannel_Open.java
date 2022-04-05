package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2020/10/18
 */
public class _22_DatagramChannel_Open {

	@Test
	public void udpClientTest() {
		try(DatagramChannel datagramChannel = DatagramChannel.open(); Selector selector = Selector.open()) {
			datagramChannel.configureBlocking(false);
			datagramChannel.register(selector, SelectionKey.OP_WRITE);

			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if (selectionKey.isWritable()) {
					datagramChannel.send(ByteBuffer.wrap("我是客户端".getBytes()), new InetSocketAddress("localhost", 6666));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void udpServerTest() {
		try(DatagramChannel datagramChannel = DatagramChannel.open(); Selector selector = Selector.open()) {
			datagramChannel.configureBlocking(false);
			datagramChannel.bind(new InetSocketAddress("localhost", 6666));

			datagramChannel.register(selector, SelectionKey.OP_READ);

			boolean isRun = true;
			while (isRun) {
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();

				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					if (selectionKey.isReadable()) {
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						datagramChannel.receive(byteBuffer);
						System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position()));
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
