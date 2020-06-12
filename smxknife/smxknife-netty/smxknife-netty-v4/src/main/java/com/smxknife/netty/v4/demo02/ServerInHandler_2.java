package com.smxknife.netty.v4.demo02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author smxknife
 * 2018-12-20
 */
public class ServerInHandler_2 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelActive..." + ctx.channel().id());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(this.getClass().getCanonicalName() + " channelRead...");
		ByteBuf byteBuf = (ByteBuf) msg;
		String s = byteBuf.toString(CharsetUtil.UTF_8);
		System.out.println("s = " + s);
		if ("2".equals(s)) {
			ctx.pipeline().remove(this);
		}
		ctx.fireChannelRead(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
