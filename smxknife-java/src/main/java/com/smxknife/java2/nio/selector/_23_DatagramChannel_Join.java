package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
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
public class _23_DatagramChannel_Join {

	@Test
	public void udpClientTest() {
		try(DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET); Selector selector = Selector.open()) {

			datagramChannel.connect(new InetSocketAddress("224.0.0.5", 6666));

			datagramChannel.configureBlocking(false);
			datagramChannel.register(selector, SelectionKey.OP_WRITE);

			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if (selectionKey.isWritable()) {
					datagramChannel.write(ByteBuffer.wrap("我是客户端".getBytes()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void udpServerTest() {
		InetSocketAddress group = new InetSocketAddress("224.0.0.5", 6666);
		try(DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET); Selector selector = Selector.open()) {
			// datagramChannel.join(InetAddress.getByName("224.0.0.5"), NetworkInterface.getByInetAddress(InetAddress.getByName("192.168.32.21"))); // 这里是公司地址
			datagramChannel.join(group.getAddress(), NetworkInterface.getByInetAddress(InetAddress.getByName("192.168.32.21"))); // 这里是公司地址

			datagramChannel.configureBlocking(false);
			// 必须执行bind操作，否则客户端发送数据接收不到
			datagramChannel.bind(group);

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
