package com.smxknife.netty.demo12;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author smxknife
 * 2018-12-10
 */
public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	private static final String[] DICTIONARY = {
			"只要功夫深，铁棒磨成针。",
			"旧时王谢堂前燕，飞入寻常百姓家。",
			"洛阳亲友如相问，一片冰心在玉壶。",
			"一寸光阴一寸金，寸金难买寸光阴。"};

	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
		String req = datagramPacket.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if ("谚语".equals(req)) {
			channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("结果：" + nextQuote(), CharsetUtil.UTF_8), datagramPacket.sender()));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
