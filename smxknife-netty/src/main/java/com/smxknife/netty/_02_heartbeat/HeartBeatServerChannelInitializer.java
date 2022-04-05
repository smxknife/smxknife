package com.smxknife.netty._02_heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/7/1
 */
public class HeartBeatServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
				.addLast("handler", new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS))
				.addLast("decoder", new StringDecoder())
				.addLast("encoder", new StringEncoder())
				.addLast("heartBeatServerHandler", new HeartBeatServerHandler());
	}
}
