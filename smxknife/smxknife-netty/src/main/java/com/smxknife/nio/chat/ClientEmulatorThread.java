package com.smxknife.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2018-11-26
 */
public class ClientEmulatorThread implements Runnable {

	private int port;
	private String ip;
	private SocketChannel socketChannel;
	private Selector selector;
	private boolean stop = false;
	Random random;

	public ClientEmulatorThread(int port, String ip) throws IOException {
		this.port = port;
		this.ip = ip;
		random = new Random();

		selector = Selector.open();
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
	}

	public void close() {
		stop = true;
		if (selector != null) {
			try {
				selector.close();
				selector = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void connect() {
		try {
			boolean connected = socketChannel.connect(new InetSocketAddress(ip, port));
			if (connected) {
				System.out.println("《connected》socketChannel has connected!");
				socketChannel.register(selector, SelectionKey.OP_READ);
			} else {
				System.out.println("《not connected》socketChannel not connected!");
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		connect();
		while (!stop) {
			try {
				int readyChannelCount = selector.select(3000L);
//				System.out.println("《Client readyChannel Count》=========== " + readyChannelCount);
				if (readyChannelCount == 0) continue;

				Set<SelectionKey> keys = selector.keys();
				System.out.println("selector keys: " + keys);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				System.out.println("Selection keys: " + selectionKeys);

				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					selectionKeyHandler(key);
				}

			} catch (IOException e) {
				e.printStackTrace();
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

	private void selectionKeyHandler(SelectionKey key) throws IOException {
		if (!key.isValid()) {
			System.out.println("key is invalid");
			return;
		}

		SocketChannel sc = (SocketChannel) key.channel();
		if (key.isConnectable()) {
			if (sc.finishConnect()) {
				System.out.println("client finish connect");
				sc.register(selector, SelectionKey.OP_READ);
				writeMsg(sc);
			} else {
				System.exit(-1);
			}
		} else if (key.isReadable()) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int read = sc.read(byteBuffer);
			if (read > 0) {
				byteBuffer.flip();
				byte[] bytes = new byte[byteBuffer.remaining()];
				byteBuffer.get(bytes);
				String response = new String(bytes);
				System.out.println(response);
				this.stop = true;
			} else if (read < 0) {
				System.out.println("read < 0");
				key.cancel();
				sc.close();
			}

		}
	}

	private void writeMsg(SocketChannel sc) throws IOException {
		System.out.println("begin write msg");
		String content = random.ints(10, 0, 10).boxed().map(it -> "msg" + it).collect(Collectors.joining(", "));
		ByteBuffer byteBuffer = ByteBuffer.allocate(content.getBytes().length);
		byteBuffer.put(content.getBytes());
		byteBuffer.flip();
		sc.write(byteBuffer);

		if (!byteBuffer.hasRemaining()) {
			System.out.printf("Msg: [ %s ] write success!\r\n", content);
		}
	}
}
