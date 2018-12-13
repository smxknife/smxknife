package com.smxknife.netty.demo13;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author smxknife
 * 2018-12-12
 */
public class NettyServer {
	public void bind() throws InterruptedException {
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
							socketChannel.pipeline()
									.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0).setName("server"))
									.addLast(new NettyMessageEncoder().setName("server"))
									.addLast("readTimeoutHandler", new ReadTimeoutHandler(50))
									.addLast(new LoginAuthRespHandler())
									.addLast("HeartBeatHandler", new HeartBeatRespHandler());

						}
					});
			ChannelFuture future = bootstrap.bind(NettyConstant.REMOTE_IP, NettyConstant.REMOTE_PORT).sync();
			System.out.println("netty server start ok : " + NettyConstant.REMOTE_IP + " : " + NettyConstant.REMOTE_PORT);
		} finally {
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new NettyServer().bind();
	}
}
