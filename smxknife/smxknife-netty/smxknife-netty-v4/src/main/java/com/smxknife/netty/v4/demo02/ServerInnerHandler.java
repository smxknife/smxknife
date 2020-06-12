package com.smxknife.netty.v4.demo02;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author smxknife
 * 2020/3/30
 */
public class ServerInnerHandler extends ChannelInboundHandlerAdapter {

	private ServerDynamicInHandler serverDynamicInHandler = new ServerDynamicInHandler();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelActive...");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("register: " + ctx.channel().id());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelRead |||||..." + msg);

		Channel channel = (Channel) msg;
		System.out.println(channel.id());

		channel.pipeline().addFirst(serverDynamicInHandler);

		System.out.println(channel.pipeline().toMap());

		ctx.fireChannelRead(msg);
	}
}
