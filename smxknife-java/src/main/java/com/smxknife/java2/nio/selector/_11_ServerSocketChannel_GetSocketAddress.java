package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _11_ServerSocketChannel_GetSocketAddress {

	@Test
	public void getSocketAddressTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.bind(new InetSocketAddress("localhost", 6666));

			SocketAddress socketAddress = serverSocketChannel.getLocalAddress();
			System.out.println(socketAddress.getClass().getName());
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
			System.out.println(inetSocketAddress.getAddress());
			System.out.println(inetSocketAddress.getHostName());
			System.out.println(inetSocketAddress.getHostString());
			System.out.println(inetSocketAddress.getPort());

			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetSocketAddress.getAddress());
			System.out.println(networkInterface.getName());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
