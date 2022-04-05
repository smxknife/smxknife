package com.smxknife.java2.nio.demo01;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2019/9/10
 */
public interface ClientSelectionKeyHandler extends SelectionKeyHandler {

	@Override
	default void handle(SelectionKey key, Nio nio) throws Exception {
		if (!key.isValid()) {
			System.out.println("SelectionKey is inValid | key = " + key);
			return;
		}

		if (key.isConnectable()) {
			SocketChannel sc = (SocketChannel)key.channel();
			if (sc.finishConnect()) {
				sc.register(key.selector(), SelectionKey.OP_READ);
				doWrite(sc, String.format(Constant.CLIENT_CONNECT_TO_SERVER_MSG_TEMPLATE, nio.getName(), LocalDateTime.now().toString()));
			} else {
				System.exit(1);
			}
		}

		if (key.isReadable()) {
			String content = doRead(key);
			System.out.println(content);
		}

	}
}
