package com.smxknife.netty.v4.demo02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author smxknife
 * 2020/4/28
 */
public class ServerDynamicInHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
		ByteBuf byteBuf = (ByteBuf) object;
		System.out.println(ctx.channel().id() + "_hhhhhh - hahah::::" + byteBuf.toString(CharsetUtil.UTF_8));
		ctx.fireChannelRead(object);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("T1");
	}

	@Override
	public boolean isSharable() {
		return true;
	}
}
