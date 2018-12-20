package com.smxknife.netty.v5.demo13;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 握手应答
 * @author smxknife
 * 2018-12-11
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

	private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

	private String[] whiteList = {"127.0.0.1", "192.168.1.104"};

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 如果是握手请求消息，处理，其他消息，透传
		if (message.getHeader() != null && message.getHeader().getType() == Header.MessageType.LOGIN_REQ.getCode()) {
			String nodeIndex = ctx.channel().remoteAddress().toString();
			System.out.println("nodeIndex : " + nodeIndex);
			NettyMessage loginResp = null;
			// 重复登录拒绝
			if (nodeCheck.containsKey(nodeIndex)) {
				loginResp = buildResponse((byte) -1);
			} else {
				InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
				String ip = address.getAddress().getHostAddress();
				boolean isOK = Arrays.asList(whiteList).contains(ip);
				// 是否在白名单
				loginResp = isOK ? buildResponse((byte) 0) : buildResponse((byte) -1);
				if (isOK) nodeCheck.put(nodeIndex, true);
			}
			System.out.println("the login response is : " + loginResp + " body : " + loginResp.getBody());
			ctx.writeAndFlush(loginResp);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildResponse(byte body) {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(Header.MessageType.LOGIN_RESP.getCode());
		message.setHeader(header);
		message.setBody(body);
		return message;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		nodeCheck.remove(ctx.channel().remoteAddress().toString());
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
}
