package com.smxknife.netty.v5.demo13;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 握手消息的接入和安全认证，在通道激活时发起握手请求
 * @author smxknife
 * 2018-12-11
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 如果是握手应答消息，需要判断是否成功
		if (message.getHeader() != null
				&& message.getHeader().getType() == Header.MessageType.LOGIN_RESP.getCode()) {
			byte loginResult = (byte)message.getBody();
			if (loginResult != (byte)0) {
				// 握手失败，关闭链接
				ctx.close();
			} else {
				System.out.println("login is ok : " + message);
				ctx.fireChannelRead(msg);
			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildLoginReq() {
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		header.setType(Header.MessageType.LOGIN_REQ.getCode());
		msg.setHeader(header);
		return msg;
	}
}
