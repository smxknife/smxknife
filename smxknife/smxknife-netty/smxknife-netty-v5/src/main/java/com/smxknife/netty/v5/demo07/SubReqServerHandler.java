package com.smxknife.netty.v5.demo07;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2018-12-04
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReq req = (SubscribeReq) msg;
		if ("admin".equals(req.getUserName())) {
			System.out.println("Server accept client subscribe req : [" + req.toString() + " ]");
		}
		ctx.writeAndFlush(resp(req));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		ctx.close();
	}

	private SubscribeResp resp(SubscribeReq req) {
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqId(req.getSubReqId());
		resp.setRespCode(10);
		resp.setDesc("Order success!");
		return resp;
	}
}
