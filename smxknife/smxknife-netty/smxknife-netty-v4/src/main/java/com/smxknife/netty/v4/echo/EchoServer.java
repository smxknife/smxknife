package com.smxknife.netty.v4.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author smxknife
 * 2020/2/14
 */
public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 10000;
		EchoServer echoServer = new EchoServer(port);

		echoServer.start();

	}

	private void start() throws InterruptedException {
		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		// 创建EventLoop
		EventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		try {
			bootstrap.group(group)
					.channel(NioServerSocketChannel.class) // 指定使用NIO传输Channel
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(echoServerHandler);
						}
					});
			ChannelFuture future = bootstrap.bind().sync(); // 同步的绑定服务器，调用sync阻塞直到绑定完成
			future.channel().closeFuture().sync(); // 获取channel的closeFuture，并阻塞当前线程直到完成
		} finally {
			group.shutdownGracefully().sync();
		}

	}
}
