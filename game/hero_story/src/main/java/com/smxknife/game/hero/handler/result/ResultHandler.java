package com.smxknife.game.hero.handler.result;

import com.google.protobuf.AbstractMessage;
import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2021/5/7
 */
public interface ResultHandler<T extends AbstractMessage> {

	/**
	 * 支持编码
	 * @return
	 */
	GameMsgProtocol.MsgCode code();

	/**
	 * 处理消息
	 * @param ctx
	 * @param msg
	 */
	void handle(ServerContext serverContext, ChannelHandlerContext ctx, T msg);

	/**
	 * 编码
	 * @param msg
	 * @return
	 */
	byte[] encode(T msg);
}
