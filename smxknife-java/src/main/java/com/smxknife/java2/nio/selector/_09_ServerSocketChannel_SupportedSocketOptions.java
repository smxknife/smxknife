package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _09_ServerSocketChannel_SupportedSocketOptions {

	@Test
	public void supportedSocketOptionsTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			Set<SocketOption<?>> socketOptions = serverSocketChannel.supportedOptions();
			socketOptions.forEach(so -> {
				System.out.printf("SocketOption name = %s, type = %s, class = %s\r\n", so.name(), so.type(), so.getClass().getName());
			});
			System.out.println("--------------------------------------");

			serverSocketChannel.bind(new InetSocketAddress(6666));
			SocketChannel socketChannel = serverSocketChannel.accept();
			Set<SocketOption<?>> socketOptions1 = socketChannel.supportedOptions();
			socketOptions1.forEach(so -> {
				System.out.printf("SocketOption name = %s, type = %s, class = %s\r\n", so.name(), so.type(), so.getClass().getName());
			});
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void clientTest() {
		try(Socket socket = new Socket("localhost", 6666)) {

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
