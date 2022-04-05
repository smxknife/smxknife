package com.smxknife.game.hero.handler.cmd;

import com.google.protobuf.AbstractMessage;
import com.smxknife.game.hero.ServerContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.log4j.Log4j2;

/**
 * @author smxknife
 * 2021/5/7
 */
@Log4j2
public abstract class AbstractCmdHandler<T extends AbstractMessage> implements CmdHandler<T> {

	@Override
	public void handle(ServerContext serverContext, ChannelHandlerContext ctx, T msg) {
		if (log.isDebugEnabled()) {
			log.debug("receive cmdClass : {}", msg.getClass().getName());
		}
		doHandle(serverContext, ctx, msg);
	}

	/**
	 * doHandle
	 * @param serverContext
	 * @param ctx
	 * @param msg
	 */
	protected abstract void doHandle(ServerContext serverContext, ChannelHandlerContext ctx, T msg);
}
