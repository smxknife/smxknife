package com.smxknife.game.hero.handler;

import com.google.protobuf.AbstractMessage;
import com.smxknife.game.hero.protocol.GameMsgProtocol;

/**
 * @author smxknife
 * 2021/5/7
 */
public final class MsgWrapper {
	private GameMsgProtocol.MsgCode msgCode;
	private AbstractMessage message;

	public MsgWrapper(GameMsgProtocol.MsgCode msgCode, AbstractMessage message) {
		this.message = message;
		this.msgCode = msgCode;
	}

	public GameMsgProtocol.MsgCode getMsgCode() {
		return msgCode;
	}

	public AbstractMessage getMessage() {
		return message;
	}
}
