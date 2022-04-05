package com.smxknife.netty._02_heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author smxknife
 * 2021/7/1
 */
public class HeartBeatClient {
	private int port;
	private String host;

	public HeartBeatClient(int port, String host) {
		this.port = port;
		this.host = host;
	}

	public void start() {
		NioEventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap()
				.group(group)
				.channel(NioSocketChannel.class)
				.handler(new HeartBeatsClientChannelInitializer());

		try {
			final ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		new HeartBeatClient(8899, "127.0.0.1").start();
	}
}
