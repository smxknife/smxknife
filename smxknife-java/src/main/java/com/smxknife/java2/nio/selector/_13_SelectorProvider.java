package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _13_SelectorProvider {

	@Test
	public void providerTest() throws IOException {
		SelectorProvider provider1 = SelectorProvider.provider();
		System.out.println(provider1);

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		SelectorProvider provider2 = serverSocketChannel.provider();
		System.out.println(provider2);
		serverSocketChannel.close();

	}
}
