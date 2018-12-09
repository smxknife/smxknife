package com.smxknife.netty.demo04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2018-12-04
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("TimeServer receive msg : " + body);

		String resp = ("<Response> at " + LocalDateTime.now().toString() + "#_");
		ByteBuf byteBuf = Unpooled.copiedBuffer(resp.getBytes());
		ctx.writeAndFlush(byteBuf);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
}
