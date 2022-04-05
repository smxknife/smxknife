package com.smxknife.game.hero.handler.result;

import com.smxknife.game.hero.protocol.GameMsgProtocol;

/**
 * @author smxknife
 * 2021/5/7
 */
public class UserMoveToResultHandler extends AbstractResultHandler<GameMsgProtocol.UserMoveToResult>{

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.USER_MOVE_TO_RESULT;
	}
}
