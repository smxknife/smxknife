package com.smxknife.netty.v5.demo08;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;

/**
 * @author smxknife
 * 2018-12-05
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ctx.write(subReq(i));
		}
		ctx.flush();
	}

	private SubscribeReqProto.SubscribeReq subReq(int id) {
		return SubscribeReqProto.SubscribeReq.newBuilder()
				.setSubReqId(id)
				.setUserName("admin")
				.setProductName("Netty welcome")
				.addAllAddress(Arrays.asList("zhejiang", "hangzhou"))
				.build();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("[client receive server back msg : ] " + msg );
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
