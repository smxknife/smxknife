package com.smxknife.netty.v4.demo01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author smxknife
 * 2018-12-20
 */
public class Client {

	public void connect(int port, String host) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new ClientHandler());
						}
					});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture channelFuture) throws Exception {
					if (channelFuture.isSuccess()) {
						System.out.println("success");
						ByteBuf byteBuf = Unpooled.copiedBuffer("SUCCESS", CharsetUtil.UTF_8);
						channelFuture.channel().writeAndFlush(byteBuf);
						channelFuture.channel().close();
					} else {
						Throwable cause = channelFuture.cause();
						cause.printStackTrace();
					}
				}
			});
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Client().connect(8080, "localhost");
	}
}
