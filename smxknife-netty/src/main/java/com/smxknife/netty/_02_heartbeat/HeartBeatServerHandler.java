package com.smxknife.netty._02_heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author smxknife
 * 2021/7/1
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

	private int loss_connect_time = 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("this = " + this + " | " + ctx.channel().remoteAddress() + "Server :" + msg.toString());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent){
			//服务端对应着读事件，当为READER_IDLE时触发
			IdleStateEvent event = (IdleStateEvent)evt;

			switch (event.state()) {
				case READER_IDLE:
					readIdleEventHandle(ctx);
					break;
				case WRITER_IDLE:
					writeIdleEventHandle(ctx);
					break;
				case ALL_IDLE:
					allIdleEventHandle(ctx);
					break;
				default:
					super.userEventTriggered(ctx,evt);
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	private void allIdleEventHandle(ChannelHandlerContext ctx) {
		System.out.println("All超时");
	}

	private void writeIdleEventHandle(ChannelHandlerContext ctx) {
		System.out.println("写超时");
	}

	private void readIdleEventHandle(ChannelHandlerContext ctx) {
		loss_connect_time++;
		System.out.println("接收消息超时");
		if(loss_connect_time > 2){
			System.out.println("关闭不活动的链接");
			ctx.channel().close();
		}
	}
}
