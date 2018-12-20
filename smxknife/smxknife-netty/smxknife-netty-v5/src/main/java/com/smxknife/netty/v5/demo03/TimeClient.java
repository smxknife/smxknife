package com.smxknife.netty.v5.demo03;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author smxknife
 * 2018-12-03
 */
public class TimeClient {

	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							// TODO add
							socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
							socketChannel.pipeline().addLast(new StringDecoder());

							socketChannel.pipeline().addLast(new TimeClientHandler());
						}
					});
			// 配置异步连接操作
			ChannelFuture future = b.connect(host, port).sync();

			// 等待客户端链路关闭
			future.channel().closeFuture().sync();

		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		String host = "127.0.0.1";

		new TimeClient().connect(port, host);
	}
}
