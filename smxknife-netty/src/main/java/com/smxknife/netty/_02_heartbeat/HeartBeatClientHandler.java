package com.smxknife.netty._02_heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalTime;

/**
 * @author smxknife
 * 2021/7/1
 */
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HeartBeat", CharsetUtil.UTF_8));

	private static final int TRY_TIMES = 3;

	private int currentTime = 0;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println(Thread.currentThread().getName() + " | this = " + this + " | 激活时间 = " + LocalTime.now());
		System.out.println(Thread.currentThread().getName() + " | this = " + this + " | 已经激活");
		ctx.fireChannelActive();

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(Thread.currentThread().getName() + " | this = " + this + " | 停止时间 = " + LocalTime.now());
		System.out.println(Thread.currentThread().getName() + " | this = " + this + " | 已经关闭");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println(Thread.currentThread().getName() + " | this = " + this + " | 当前轮训时间 = " + LocalTime.now());
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;

			switch (event.state()) {
				case READER_IDLE:
					System.out.println("read idle");
					break;
				case WRITER_IDLE:
					if (currentTime <= TRY_TIMES) {
						System.out.println("当前次数：" + currentTime++);
						ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
					}
					break;
				case ALL_IDLE:
					System.out.println("all idle");
					break;
				default:
					ctx.fireUserEventTriggered(evt);
			}
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = msg.toString();
		System.out.println(message);
		if (message.equals("HeartBeat")) {
			ctx.write("has read message from server");
			ctx.flush();
		}

		ReferenceCountUtil.release(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
