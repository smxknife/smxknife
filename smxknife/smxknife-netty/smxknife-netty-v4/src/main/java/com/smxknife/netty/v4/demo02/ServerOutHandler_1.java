package com.smxknife.netty.v4.demo02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author smxknife
 * 2018-12-20
 */
public class ServerOutHandler_1 extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channel write... | " + String.valueOf(msg));
		promise.setSuccess();
		ctx.writeAndFlush("wwww_1" + String.valueOf(msg));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
