package com.smxknife.java2.nio.selector2.sel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author smxknife
 * 2021/4/23
 */
public class Selector1Server {

	private static volatile boolean iSDebuggable = true;

	public static void main(String[] args) throws IOException {

		if (Objects.isNull(args)) {
			System.out.println("args is null");
		} else if (args.length == 0) {
			System.out.println("args length is 0");
		} else {
			String arg = args[0];
			iSDebuggable = Boolean.valueOf(arg);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			ServerSocketChannel serverSocketChannel = null;
			SocketChannel socketChannel = null;

			public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
				this.serverSocketChannel = serverSocketChannel;
			}

			public void setSocketChannel(SocketChannel socketChannel) {
				this.socketChannel = socketChannel;
			}

			@Override
			public void run() {
				if (Objects.nonNull(this.socketChannel) && this.socketChannel.isOpen()) {
					try {
						this.socketChannel.close();
						System.out.println("close1_SocketChannel_close");
						debug();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (Objects.nonNull(this.serverSocketChannel) && this.serverSocketChannel.isOpen()) {
					try {
						this.serverSocketChannel.close();
						System.out.println("close1_ServerSocketChannel_close");
						debug();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});


		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(10001));

		System.out.println("serverSocketChannel after bind");
		debug();

		/**
		 * 进行系统调用：
		 * - epoll_create(256) = 7 : 创建了文件描述符7
		 * - epoll_ctl(7, EPOLL_CTL_ADD, 5, {EPOLLIN, {u32=5, u64=140698833649669}}) = 0 将文件描述符7添加了EPOLLIN监听
		 */
		Selector selector = Selector.open();
		System.out.println("selector after open");
		debug();

		/**
		 * 这里没有进行系统调用
		 */
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("serverSocketChannel after register");
		debug();

		while (true) {

			System.out.println("selector before select");
			/**
			 * 进行了系统调用
			 * - epoll_ctl(7, EPOLL_CTL_ADD, 4, {EPOLLIN, {u32=4, u64=140698833649668}}) = 0
			 * - epoll_wait(7, 这里产生了阻塞，等待epoll返回
			 * 当有客户端接入后从阻塞继续执行
			 * - epoll_wait(7, [{EPOLLIN, {u32=4, u64=140698833649668}}], 8192, -1) = 1
			 */
			while (selector.select() > 0) {
				System.out.println("selector after select");
				debug();

				/**
				 * 这里不会产生系统调用
				 */
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				System.out.println("selector selectedKeys");
				debug();

				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					if (selectionKey.isAcceptable()) {
						acceptHandle(selector, selectionKey);
					} else if (selectionKey.isReadable()) {
						readHanlde(selectionKey);
					}
				}
			}
		}

	}

	private static void readHanlde(SelectionKey selectionKey) {
		SocketChannel channel = (SocketChannel)selectionKey.channel();
		ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
		buffer.clear();

		while (true) {
			try {
				System.out.println("SocketChannel before read");
				int read = channel.read(buffer);
				System.out.println("SocketChannel after read");
				debug();

				if (read > 0) {
					buffer.flip();
					while (buffer.hasRemaining()) {
						channel.write(buffer);
					}
					buffer.clear();
				} else if (read == 0) {
					break;
				} else if (read == -1) {
					channel.close();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void acceptHandle(Selector selector, SelectionKey selectionKey) {
		System.out.println("SelectionKey before channel ");
		/**
		 * 这里也不会产生系统调用
		 */
		ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
		System.out.println("SelectionKey after channel ");

		try {
			debug();

			System.out.println("ServerSocketChannel before accept");
			/**
			 * accept进行了系统调用：
			 * - accept(4, {sa_family=AF_INET6, sin6_port=htons(57226), inet_pton(AF_INET6, "::1", &sin6_addr), sin6_flowinfo=0, sin6_scope_id=0}, [28]) = 8
			 * - fcntl(8, F_GETFL)                       = 0x2 (flags O_RDWR)
			 */
			SocketChannel socketChannel = channel.accept();
			System.out.println("ServerSocketChannel after accept");
			debug();


			socketChannel.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			socketChannel.register(selector, SelectionKey.OP_READ, buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void debug() throws IOException {
		if (iSDebuggable) {
			System.in.read();
		}
	}
}
