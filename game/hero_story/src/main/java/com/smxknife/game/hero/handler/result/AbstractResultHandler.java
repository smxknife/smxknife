package com.smxknife.game.hero.handler.result;

import com.google.protobuf.AbstractMessage;
import com.smxknife.game.hero.ServerContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2021/5/7
 */
public abstract class AbstractResultHandler<T extends AbstractMessage> implements ResultHandler<T> {

	@Override
	public final void handle(ServerContext serverContext, ChannelHandlerContext ctx, T msg) {
		// TODO
		doHandle(serverContext, ctx, msg);
	}

	/**
	 * do
	 * @param serverContext
	 * @param ctx
	 * @param msg
	 */
	protected void doHandle(ServerContext serverContext, ChannelHandlerContext ctx, T msg) {};

	@Override
	public byte[] encode(T msg) {
		return msg.toByteArray();
	}
}
