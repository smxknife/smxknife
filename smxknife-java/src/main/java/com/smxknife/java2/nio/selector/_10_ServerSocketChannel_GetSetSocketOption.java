package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _10_ServerSocketChannel_GetSetSocketOption {

	@Test
	public void getSetSocketOptionTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			System.out.println("A SO_RCVBUF = " + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
			serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1234);
			System.out.println("B SO_RCVBUF = " + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
