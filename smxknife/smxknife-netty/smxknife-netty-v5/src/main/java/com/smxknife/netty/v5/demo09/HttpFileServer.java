package com.smxknife.netty.v5.demo09;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author smxknife
 * 2018-12-09
 */
public class HttpFileServer {

	private static final String DEFAULT_URL = "/src/main/java/com/smxknife/";

	public void run(final int port, final String url) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
							socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
							socketChannel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
							socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							socketChannel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
						}
					});
			ChannelFuture future = bootstrap.bind("127.0.0.1", port).sync();
			System.out.println("Http文件目录服务器启动，网址是：http://127.0.0.1:" + port + url);
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new HttpFileServer().run(8080, DEFAULT_URL);
	}
}
