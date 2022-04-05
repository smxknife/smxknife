package com.smxknife.netty._01_simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author smxknife
 * 2021/6/19
 */
public class Server {
	public static void main(String[] args) throws InterruptedException {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

					}
				});

		serverBootstrap.bind(6666).sync();


	}
}
