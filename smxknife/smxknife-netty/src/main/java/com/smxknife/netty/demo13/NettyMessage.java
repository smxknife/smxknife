package com.smxknife.netty.demo13;

/**
 * Netty消息定义表
 * 由于心跳消息、握手请求、握手应答消息都是由NettyMessage承载，不需要单独定义数据结构
 * @author smxknife
 * 2018-12-11
 */
public final class NettyMessage {
	private Header header; // 消息头
	private Object body; // 消息体

	public final Header getHeader() {
		return header;
	}

	public final void setHeader(Header header) {
		this.header = header;
	}

	public final Object getBody() {
		return body;
	}

	public final void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "NettyMessage {" +
				"header=" + header +
				", body=" + body +
				'}';
	}
}
