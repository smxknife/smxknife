package com.smxknife.netty.v4.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/3/31
 */
public class ChatServer {
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;

	public ChannelFuture start(InetSocketAddress address) {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(group)
				.channel(NioServerSocketChannel.class)
				.childHandler(createInitializer(channelGroup));
		ChannelFuture channelFuture = serverBootstrap.bind(address);
		channelFuture.syncUninterruptibly();
		channel = channelFuture.channel();
		return channelFuture;
	}

	private ChannelHandler createInitializer(ChannelGroup channelGroup) {
		return new ChatServerInitializer(channelGroup);
	}

	public void destroy() {
		if (Objects.nonNull(channel)) {
			channel.close();
		}
		channelGroup.close();
		group.shutdownGracefully();
	}

	public static void main(String[] args) {
		int port = 8888;
		final ChatServer endpoint = new ChatServer();
		ChannelFuture channelFuture = endpoint.start(new InetSocketAddress(port));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			endpoint.destroy();
		}));
		channelFuture.channel().closeFuture().syncUninterruptibly();
	}
}
