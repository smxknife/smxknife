package com.smxknife.netty.demo05;

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
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("EchoServer receive msg : " + body);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		String response = "<Server receive> at " + LocalDateTime.now().toString();
		ByteBuf byteBuf = Unpooled.buffer(response.getBytes().length);
		byteBuf.writeBytes(response.getBytes());
		ctx.writeAndFlush(byteBuf);
	}
}
