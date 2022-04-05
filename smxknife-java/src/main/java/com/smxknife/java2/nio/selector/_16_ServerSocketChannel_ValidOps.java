package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _16_ServerSocketChannel_ValidOps {

	/**
	 * 说明：只有输出结果为0，表示支持
	 * 从输出结果来看，ServerSocketChannel只支持ACCEPT，而SocketChannel支持除了ACCEPT之外的三种
	 */
	@Test
	public void validOpsTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    SocketChannel socketChannel = SocketChannel.open();
		    Selector selector1 = Selector.open()) {

			int ops1 = serverSocketChannel.validOps();
			int ops2 = socketChannel.validOps();

			System.out.println("ops1 = " + ops1);
			System.out.println("ops2 = " + ops2);

			System.out.println("ServerSocketChannel ops ...... ");
			System.out.println(SelectionKey.OP_ACCEPT & ~serverSocketChannel.validOps());
			System.out.println(SelectionKey.OP_CONNECT & ~serverSocketChannel.validOps());
			System.out.println(SelectionKey.OP_READ & ~serverSocketChannel.validOps());
			System.out.println(SelectionKey.OP_WRITE & ~serverSocketChannel.validOps());

			System.out.println("SocketChannel ops .......");
			System.out.println(SelectionKey.OP_ACCEPT & ~socketChannel.validOps());
			System.out.println(SelectionKey.OP_CONNECT & ~socketChannel.validOps());
			System.out.println(SelectionKey.OP_READ & ~socketChannel.validOps());
			System.out.println(SelectionKey.OP_WRITE & ~socketChannel.validOps());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
