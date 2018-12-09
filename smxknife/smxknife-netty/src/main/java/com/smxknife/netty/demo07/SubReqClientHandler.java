package com.smxknife.netty.demo07;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2018-12-04
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ctx.write(subReq(i));
		}
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("[Receive server response] : " + msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private SubscribeReq subReq(int i) {
		SubscribeReq req = new SubscribeReq();
		req.setAddress("浙江省杭州市滨江区");
		req.setPhoneNumber("183xxxxxxxx");
		req.setProductName("Netty welcome");
		req.setSubReqId(i);
		req.setUserName("admin");
		return req;
	}
}
