package com.smxknife.netty.v5.demo08;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2018-12-05
 */
public class SubServerReqHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
		if ("admin".equals(req.getUserName())) {
			System.out.println("[server receive order : ]" + req.toString());
			ctx.writeAndFlush(resp(req.getSubReqId()));
		}
	}

	private SubscribeRespProto.SubscribeResp resp(int subReqId) {
		return SubscribeRespProto.SubscribeResp.newBuilder()
				.setSubReqId(subReqId)
				.setRespCode("0")
				.setDesc("order receive success!")
				.build();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
