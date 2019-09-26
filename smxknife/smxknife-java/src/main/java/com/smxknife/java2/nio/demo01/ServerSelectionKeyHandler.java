package com.smxknife.java2.nio.demo01;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2019/9/10
 */
public interface ServerSelectionKeyHandler extends SelectionKeyHandler {

	@Override
	default void handle(SelectionKey key, Nio nio) throws Exception {
		if (!key.isValid()) {
			System.out.println("SelectionKey is inValid | key = " + key);
			return;
		}

		if (key.isAcceptable()) {
			doAccept(key);
		}
		if (key.isReadable()) {
			doRead(key);
			doWrite((SocketChannel) key.channel(), "server received | " + LocalDateTime.now());
		}
	}
}
