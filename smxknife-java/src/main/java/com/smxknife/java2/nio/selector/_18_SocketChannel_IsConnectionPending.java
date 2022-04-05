package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _18_SocketChannel_IsConnectionPending {

	@Test
	public void connectClientWithBlockingTest() {
		long begin = 0;
		long end = 0;
		try(SocketChannel socketChannel = SocketChannel.open()) {
			System.out.println("A isConnectionPending | " + socketChannel.isConnectionPending());
			begin  = System.currentTimeMillis();
			System.out.println("before connect " + begin);
			socketChannel.connect(new InetSocketAddress("localhost", 6666));
			System.out.println("B isConnectionPending | " + socketChannel.isConnectionPending());
			end = System.currentTimeMillis();
			System.out.println("after connect " + end);
			System.out.println("end - begin = " + (end - begin));

		} catch (IOException e) {
			end = System.currentTimeMillis();
			System.out.println("exception connect " + end);
			System.out.println("end - begin = " + (end - begin));
			e.printStackTrace();
		}
	}

	@Test
	public void connectClientWithNonBlockingTest() {
		long begin = 0;
		long end = 0;
		try(SocketChannel socketChannel = SocketChannel.open()) {
			socketChannel.configureBlocking(false);
			System.out.println("A isConnectionPending | " + socketChannel.isConnectionPending());
			begin  = System.currentTimeMillis();
			System.out.println("before connect " + begin);
			socketChannel.connect(new InetSocketAddress("localhost", 6666));
			System.out.println("B isConnectionPending | " + socketChannel.isConnectionPending());
			end = System.currentTimeMillis();
			System.out.println("after connect " + end);
			System.out.println("end - begin = " + (end - begin));

		} catch (IOException e) {
			end = System.currentTimeMillis();
			System.out.println("exception connect " + end);
			System.out.println("end - begin = " + (end - begin));
			e.printStackTrace();
		}
	}

	@Test
	public void connectServerTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.bind(new InetSocketAddress(6666));
			SocketChannel socketChannel = serverSocketChannel.accept();
			SocketAddress remoteAddress = socketChannel.getRemoteAddress();
			System.out.println(remoteAddress);
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
