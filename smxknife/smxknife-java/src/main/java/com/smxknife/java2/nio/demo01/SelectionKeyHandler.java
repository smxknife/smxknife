package com.smxknife.java2.nio.demo01;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2019/9/10
 */
public interface SelectionKeyHandler {
	/**
	 * handle key event
	 * @param key
	 * @throws Exception error
	 */
	void handle(SelectionKey key, Nio nio) throws Exception;

	/**
	 * connect
	 * @param socketChannel
	 * @param selector
	 * @param socketAddress
	 * @return
	 * @throws Exception
	 */
	default boolean doConnect(SocketChannel socketChannel, Selector selector, SocketAddress socketAddress) throws IOException {
		if (socketChannel.connect(socketAddress)) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			return true;
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			return false;
		}
	}

	/**
	 * accept
	 * @param key
	 * @throws Exception
	 */
	default void doAccept(SelectionKey key) throws IOException {
		// 处理新接入的消息
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel channel = ssc.accept();
		channel.configureBlocking(false);
		channel.register(key.selector(), SelectionKey.OP_READ);
	}

	/**
	 * read
	 * @param key
	 * @return
	 * @throws Exception error
	 */
	default String doRead(SelectionKey key) throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		int readBytes = sc.read(readBuffer);
		if (readBytes > 0) {
			readBuffer.flip();
			byte[] bytes = new byte[readBuffer.remaining()];
			readBuffer.get(bytes);
			String body = new String(bytes, "UTF-8");
			return body;
		}
		return "";
	}


	/**
	 * write
	 * @param socketChannel
	 * @param content
	 * @throws Exception error
	 */
	default void doWrite(SocketChannel socketChannel, String content) throws IOException {
		if (content != null && content.trim().length() > 0) {
			byte[] bytes = content.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			socketChannel.write(writeBuffer);
		}
	}
}
