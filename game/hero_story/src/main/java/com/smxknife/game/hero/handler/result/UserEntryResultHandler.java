package com.smxknife.game.hero.handler.result;

import com.smxknife.game.hero.protocol.GameMsgProtocol;

/**
 * @author smxknife
 * 2021/5/7
 */
public class UserEntryResultHandler extends AbstractResultHandler<GameMsgProtocol.UserEntryResult>{

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.USER_ENTRY_RESULT;
	}
}
