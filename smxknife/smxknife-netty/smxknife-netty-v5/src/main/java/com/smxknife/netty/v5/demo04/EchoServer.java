package com.smxknife.netty.v5.demo04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author smxknife
 * 2018-12-04
 */
public class EchoServer {

	public void bind(int port) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ByteBuf byteBuf = Unpooled.copiedBuffer("#_".getBytes());
							socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
							socketChannel.pipeline().addLast(new StringDecoder());
							socketChannel.pipeline().addLast(new EchoServerHandler());
						}
					});

			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		new EchoServer().bind(8080);
	}
}
