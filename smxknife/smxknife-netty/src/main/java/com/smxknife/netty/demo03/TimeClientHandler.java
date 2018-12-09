package com.smxknife.netty.demo03;

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

//	private final ByteBuf firstMessage;
	byte[] bytes;

	public TimeClientHandler() {
		bytes = ("hello, Netty" + System.getProperty("line.separator")).getBytes();
//		firstMessage = Unpooled.buffer(bytes.length);
//		firstMessage.writeBytes(bytes);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf byteBuf = null;
		for (int i = 0; i < 100; i++) {
			byteBuf = Unpooled.buffer(bytes.length);
			byteBuf.writeBytes(bytes);
			ctx.writeAndFlush(byteBuf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String response = (String) msg;
		System.out.println("[Client] " + response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warning("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}
}
