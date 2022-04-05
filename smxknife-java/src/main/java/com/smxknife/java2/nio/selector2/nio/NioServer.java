package com.smxknife.java2.nio.selector2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/4/22
 */
public class NioServer {
	private static volatile boolean iSDebuggable = true;
	public static void main(String[] args) {

		if (Objects.isNull(args)) {
			System.out.println("args is null");
		} else if (args.length == 0) {
			System.out.println("args length is 0");
		} else {
			String arg = args[0];
			iSDebuggable = Boolean.valueOf(arg);
		}

		ServerSocketChannel serverSocketChannel = null;
		SocketChannel socketChannel = null;


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

		try {
			/**
			 * ServerSocketChannel.open()
			 * 进行了系统调用：socket(AF_INET6, SOCK_STREAM, IPPROTO_IP) = 4
			 * 开辟了一个socket类型的文件描述符4
			 */
			serverSocketChannel = ServerSocketChannel.open();
			System.out.println("step1_ServerSocketChannel_open");
			debug();

			/**
			 * bind(10001)
			 * 进行了系统调用：
			 * - bind(4, {sa_family=AF_INET6, sin6_port=htons(10001), inet_pton(AF_INET6, "::", &sin6_addr), sin6_flowinfo=0, sin6_scope_id=0}, 28) = 0
			 * - listen(4, 50)                           = 0
			 * 文件描述符4由原来的socket类型变为IPv6或IPv4类型，且是LISTEN
			 */
			serverSocketChannel.bind(new InetSocketAddress(10001));
			System.out.println("step2_ServerSocketChannel_bind");
			debug();

			/**
			 * configurableBlocking(false)
			 * 进行了系统调用：
			 * - fcntl(4, F_GETFL)
			 * - fcntl(4, F_SETFL, O_RDWR|O_NONBLOCK)    = 0
			 * 将文件描述符4，设置为非阻塞
			 */
			serverSocketChannel.configureBlocking(false);
			System.out.println("step3_ServerSocketChannel_config_nonBlocking");
			debug();

			while (true) {
				TimeUnit.SECONDS.sleep(1);

				/**
				 * serverSocketChannel.accept()
				 * 进行了系统调用：accept(4, 0x7f19a40dac70, 0x7f19adcb7784) = -1
				 * 在文件描述符4，接收连接，因为设置非阻塞，立刻返回，-1：表示没有连接
				 *
				 * 如果有客户端连接进来了
				 * 进行系统调用：
				 * - accept(4, {sa_family=AF_INET6, sin6_port=htons(60100), inet_pton(AF_INET6, "::1", &sin6_addr), sin6_flowinfo=0, sin6_scope_id=0}, [28]) = 5
				 * 返回了文件描述符5，建立一个IPv6或v4类型的连接
				 * 进行系统调用：
				 * - fcntl(5, F_GETFL)
				 */
				socketChannel = serverSocketChannel.accept();
				System.out.println("step4_ServerSocketChannel_accept");
				debug();

				if (Objects.isNull(socketChannel)) {
					//
				} else {
					/**
					 * configureBlocking(false)
					 * 进行系统调用：
					 * - fcntl(5, F_GETFL)                       = 0x2 (flags O_RDWR)
					 * - fcntl(5, F_SETFL, O_RDWR|O_NONBLOCK)    = 0
					 * 设置非阻塞
					 */
					socketChannel.configureBlocking(false);
					System.out.println("step5_SocketChannel_config_nonBlocking");
					debug();
				}

				/**
				 * 这里没有任何系统调用
				 */
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				System.out.println("step6_ByteBuffer_allocate");
				debug();

				/**
				 * 进行系统调用：
				 * - socketpair(AF_LOCAL, SOCK_STREAM, 0, [6, 7]) = 0
				 * - read(5, 0x7f19a40de060, 1024)
				 * 从文件描述符5，读取1024个字节
				 */
				int readNum = socketChannel.read(byteBuffer);
				System.out.println("step7_socketChannel_read");
				debug();

				/**
				 * 下面也没有系统调用
				 */
				if (readNum > 0) {
					byteBuffer.flip();
					byte[] data = new byte[byteBuffer.limit()];
					byteBuffer.get(data);
					System.out.println("step8_readData_: " + new String(data));
					debug();
					byteBuffer.clear();
				}

			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void debug() throws IOException {
		if (iSDebuggable) {
			System.in.read();
		}
	}
}
