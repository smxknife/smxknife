package com.smxknife.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2018/11/23
 */
public class ServerHandlerThread implements Runnable {

	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private boolean stop = false;

	public ServerHandlerThread(int port) throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port), 1024);
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.printf("ChartServer start in port : %s\r\n", port);
	}

	public void stopServer() {
		this.stop = true;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				int readyChannelCount = selector.select(3000L);
//				System.out.println("《Server readyChannel Count》=============== " + readyChannelCount);

				if (0 == readyChannelCount) {
					continue;
				}

				Set<SelectionKey> keys = selector.keys();
				System.out.println("selector keys : " + keys);
				keys.forEach(k -> {
					System.out.println(k.toString() + " : " + k.isValid());
				});
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				System.out.println("selection keys : " + selectionKeys);

				System.out.println();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					System.out.println("has next!");
					SelectionKey key = iterator.next();
					iterator.remove();
					try {
						selectionKeyHandler(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void selectionKeyHandler(SelectionKey key) throws IOException {
		if (!key.isValid()) {
			System.out.println("key is inValid");
			return;
		}
		if (key.isReadable()) {
			System.out.println("key is readable");
			SocketChannel sc = (SocketChannel) key.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int read = sc.read(byteBuffer);
			if (read > 0) {
				byteBuffer.flip();
				byte[] bytes = new byte[byteBuffer.remaining()];
				byteBuffer.get(bytes);
				String body = new String(bytes);
				System.out.printf("server receive msg [ %s ]\r\n", body);
				writeMsg(sc, "【Response Msg : OK】" + body);
			}
		} else if (key.isConnectable()) {
			System.out.println("key is connectable");
		} else if (key.isAcceptable()) {
			System.out.println("key is acceptable");
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			try {
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (key.isWritable()) {
			System.out.println("key is writable");
		}
	}

	private void writeMsg(SocketChannel sc, String response) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(response.getBytes().length);
		byteBuffer.put(response.getBytes());
		byteBuffer.flip();
		sc.write(byteBuffer);
	}
}
