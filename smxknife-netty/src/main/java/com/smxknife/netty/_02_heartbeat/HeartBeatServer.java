package com.smxknife.netty._02_heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author smxknife
 * 2021/7/1
 */
public class HeartBeatServer {
	private int port;

	public HeartBeatServer(int port) {
		this.port = port;
	}

	public void start() {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new ChannelHandler() {
					@Override
					public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
						System.out.println("this = " + this + " | " + Thread.currentThread().getName() + " | handlerAdded " + ctx.name());
					}

					@Override
					public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
						System.out.println("this = " + this + " | " + Thread.currentThread().getName() + " | handlerRemoved " + ctx.name());
					}

					@Override
					public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
						System.out.println("this = " + this + " | " + Thread.currentThread().getName() + " | exceptionCaught " + ctx.name());
					}
				})
				.option(ChannelOption.SO_BACKLOG, 2)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new HeartBeatServerChannelInitializer());

		try {
			final ChannelFuture channelFuture = bootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		new HeartBeatServer(8899).start();
	}
}
