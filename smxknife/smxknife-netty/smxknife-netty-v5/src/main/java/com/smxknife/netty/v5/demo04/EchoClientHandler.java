package com.smxknife.netty.v5.demo04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2018-12-04
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

	private static final String REQ_MSG = "_Hello, Netty.#_";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			ByteBuf byteBuf = Unpooled.copiedBuffer((i + REQ_MSG).getBytes());
			ctx.writeAndFlush(byteBuf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("TimeClient : " + body);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
