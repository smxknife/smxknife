package com.smxknife.game.hero.handler.cmd;

import com.google.protobuf.AbstractMessage;
import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author smxknife
 * 2021/5/7
 */
public interface CmdHandler<T extends AbstractMessage> {

	/**
	 * 处理消息
	 * @param serverContext
	 * @param ctx
	 * @param msg
	 */
	void handle(ServerContext serverContext, ChannelHandlerContext ctx, T msg);

	/**
	 * 支持编码
	 * @return
	 */
	GameMsgProtocol.MsgCode code();

	/**
	 * 解码
	 * @param bytes
	 * @return
	 */
	MsgWrapper decode(byte[] bytes);

}
