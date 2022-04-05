package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _20_SocketChannel_FileChannel_TransferTo {

	@Test
	public void serverTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    Selector selector = Selector.open()) {
			serverSocketChannel.bind(new InetSocketAddress("localhost", 6666));
			serverSocketChannel.configureBlocking(false);

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			SocketChannel socketChannel = null;
			boolean isRun = true;
			while (isRun) {
				System.out.println("before select()");
				selector.select();
				System.out.println("after select()");

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
				while (selectionKeyIterator.hasNext()) {
					SelectionKey key = selectionKeyIterator.next();
					selectionKeyIterator.remove();

					if (key.isAcceptable()) {
						socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_WRITE);
					}

					if (key.isWritable()) {
						RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/ShaoYun/local/soft/android-studio-ide-145.3537739-mac.dmg", "r");
						System.out.println("file length = " + randomAccessFile.length());
						FileChannel fileChannel = randomAccessFile.getChannel();
						fileChannel.transferTo(0, randomAccessFile.length(), socketChannel);

						fileChannel.close();
						randomAccessFile.close();
						socketChannel.close();
					}
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void clientTest() {
		try(SocketChannel socketChannel = SocketChannel.open();Selector selector = Selector.open();) {
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("localhost", 6666));

			socketChannel.register(selector, SelectionKey.OP_CONNECT);

			boolean isRun = true;
			while (isRun) {
				System.out.println("begin selector");

				if (socketChannel.isOpen()) { // 为什么加上这个判断？
					selector.select();
					Set<SelectionKey> selectionKeys = selector.selectedKeys();

					Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
					while (selectionKeyIterator.hasNext()) {
						SelectionKey selectionKey = selectionKeyIterator.next();
						selectionKeyIterator.remove();

						if (selectionKey.isConnectable()) {
							while (!socketChannel.finishConnect()) {
								System.out.println("连接中，请稍后...");
							}
							System.out.println("连接成功!");
							socketChannel.register(selector, SelectionKey.OP_READ);
						}

						if (selectionKey.isReadable()) {
							ByteBuffer byteBuffer = ByteBuffer.allocate(50000);
							int readLength = 0;
							int total = 0;
							while ((readLength = socketChannel.read(byteBuffer)) != -1) {
								total += readLength;
								System.out.println("readLength = " + readLength + ", total = " + total);
								byteBuffer.clear();
							}
							socketChannel.close();
							System.out.println("读取结束");
						}
					}
				} else {
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
