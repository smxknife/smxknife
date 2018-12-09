package com.smxknife.netty.demo02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @author smxknife
 * 2018-12-03
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

	private final ByteBuf firstMessage;

	public TimeClientHandler() {
		byte[] bytes = "hello, Netty".getBytes();
		firstMessage = Unpooled.buffer(bytes.length);
		firstMessage.writeBytes(bytes);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		String response = new String(bytes, "UTF-8");
		System.out.println("[Client] " + response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warning("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}
}
