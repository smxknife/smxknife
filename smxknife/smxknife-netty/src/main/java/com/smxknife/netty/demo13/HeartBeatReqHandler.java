package com.smxknife.netty.demo13;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 心跳请求，客户端发送请求
 * 心跳消息是为了检验链路的可用性，因此不需要携带消息体
 * @author smxknife
 * 2018-12-11
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {
	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 握手成功，主动发送心跳消息
		if (message.getHeader() != null && message.getHeader().getType() == Header.MessageType.LOGIN_RESP.getCode()) {
			heartBeat = ctx.executor().scheduleAtFixedRate(
					new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5, TimeUnit.SECONDS);
		} else if (message.getHeader() != null && message.getHeader().getType() == Header.MessageType.HEART_BRAT_RESP.getCode()) {
			System.out.println("client receive server heart beat message : " + message);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (heartBeat != null) {
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}

	private class HeartBeatTask implements Runnable {

		private final ChannelHandlerContext ctx;

		public HeartBeatTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			NettyMessage heartBeat = buildHearBeat();
			System.out.println("client send heart beat message to server : ----> " + heartBeat);
			ctx.writeAndFlush(heartBeat);
		}

		private NettyMessage buildHearBeat() {
			NettyMessage message = new NettyMessage();
			Header header = new Header();
			header.setType(Header.MessageType.HEART_BRAT_REQ.getCode());
			message.setHeader(header);
			return message;
		}
	}
}
