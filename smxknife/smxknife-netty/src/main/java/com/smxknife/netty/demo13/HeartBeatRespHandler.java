package com.smxknife.netty.demo13;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳应答
 * @author smxknife
 * 2018-12-12
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 返回心跳应答消息
		if (message.getHeader() != null && message.getHeader().getType() == Header.MessageType.HEART_BRAT_REQ.getCode()) {
			System.out.println("server receive heart beat message : ----> " + message);
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("send heart beat response message to client : ----> " + heartBeat);
			ctx.writeAndFlush(heartBeat);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildHeartBeat() {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(Header.MessageType.HEART_BRAT_RESP.getCode());
		message.setHeader(header);
		return message;
	}


}
