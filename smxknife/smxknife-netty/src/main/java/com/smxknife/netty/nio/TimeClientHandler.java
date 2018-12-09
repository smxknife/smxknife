package com.smxknife.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2018/11/12
 */
public class TimeClientHandler implements Runnable {

	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;

	public TimeClientHandler(String host, int port) {
		this.host = (host == null || "".equals(host)) ? "127.0.0.1" : host;
		this.port = port;

		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				SelectionKey key = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					iterator.remove();
					try {
						handleInput(key);
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
				System.exit(1);
			}
		}

		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doConnect() throws IOException {
		// 如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答
		if (socketChannel.connect(new InetSocketAddress(host, port))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel socketChannel) throws IOException {
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
		byteBuffer.put(req);
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
		if (!byteBuffer.hasRemaining()) {
			System.out.println("Send order to server success");
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else {
					System.exit(1);
				}
			}
			if (key.isReadable()) {
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(byteBuffer);
				if (readBytes > 0) {
					byteBuffer.flip();
					byte[] bytes = new byte[byteBuffer.remaining()];
					byteBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("Now is " + body);
					this.stop = true;
				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				} else {}
			}
		}
	}
}
