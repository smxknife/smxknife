package com.smxknife.netty.v5.demo11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author smxknife
 * 2018-12-10
 */
public class WebSocketServer {

	public void run(int port) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							// 将请求和应答消息编码或解码为http消息
							socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
							// 将http消息的多个部分组合成一条完整的http消息
							socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
							// 来想客户端发送HTML5文件，主要用于支持浏览器和服务端进行WebSocket通信
							socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							socketChannel.pipeline().addLast("handler", new WebSocketServerHandler());
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
		new WebSocketServer().run(8080);
	}
}
