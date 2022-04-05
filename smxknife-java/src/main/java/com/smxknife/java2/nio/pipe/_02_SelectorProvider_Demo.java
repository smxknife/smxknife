package com.smxknife.java2.nio.pipe;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author smxknife
 * 2020/10/22
 */
public class _02_SelectorProvider_Demo {
	@Test
	public void test() throws IOException {
		SelectorProvider provider = SelectorProvider.provider();
		System.out.println("provider class name: " + provider.getClass().getName());
		AbstractSelector selector = provider.openSelector();
		System.out.println("selector class name: " + selector.getClass().getName());
		DatagramChannel datagramChannel = provider.openDatagramChannel();
		System.out.println("datagramChannel class name: " + datagramChannel.getClass().getName());
		DatagramChannel datagramChannel1 = provider.openDatagramChannel(StandardProtocolFamily.INET);
		System.out.println("datagramChannel1 class name: " + datagramChannel1.getClass().getName());
		DatagramChannel datagramChannel2 = provider.openDatagramChannel(StandardProtocolFamily.INET6);
		System.out.println("datagramChannel2 class name: " + datagramChannel2.getClass().getName());
		Pipe pipe = provider.openPipe();
		System.out.println("pipe class name: " + pipe.getClass().getName());
		ServerSocketChannel serverSocketChannel = provider.openServerSocketChannel();
		System.out.println("serverSocketChannel class name: " + serverSocketChannel.getClass().getName());
		SocketChannel socketChannel = provider.openSocketChannel();
		System.out.println("socketChannel class name: " + socketChannel.getClass().getName());
		Channel channel = provider.inheritedChannel();
		System.out.println("provider.inheritedChannel " + channel);


	}
}
