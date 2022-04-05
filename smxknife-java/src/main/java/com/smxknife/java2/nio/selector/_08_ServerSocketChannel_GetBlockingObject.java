package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _08_ServerSocketChannel_GetBlockingObject {

	@Test
	public void getBlockingObjectTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			// 在内部可用来防止重复注册
			Object lock = serverSocketChannel.blockingLock();
			System.out.println(lock);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
