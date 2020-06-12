package com.smxknife.netty.v4.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author smxknife
 * 2018-12-20
 */
public class ServerInHandler_3 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelActive..." + ctx.channel().id());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelRead...");
		ctx.fireChannelRead(msg);
		ctx.pipeline().write(msg);
		ctx.channel().write(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
