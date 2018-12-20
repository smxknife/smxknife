package com.smxknife.netty.v5.demo04;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author smxknife
 * 2018-12-04
 */
public class EchoClient {

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
							ByteBuf byteBuf = Unpooled.copiedBuffer("#_".getBytes());
							socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
							socketChannel.pipeline().addLast(new StringDecoder());
							socketChannel.pipeline().addLast(new EchoClientHandler());
						}
					});
			ChannelFuture future = bootstrap.connect(host, port).sync();

			future.channel().closeFuture().sync();

		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		String host = "localhost";

		new EchoClient().connect(port, host);
	}
}
