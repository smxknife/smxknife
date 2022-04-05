package com.smxknife.game.hero.handler.result;

import com.smxknife.game.hero.protocol.GameMsgProtocol;

/**
 * @author smxknife
 * 2021/5/7
 */
public class UserQuitResultHandler extends AbstractResultHandler<GameMsgProtocol.UserQuitResult>{

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.USER_QUIT_RESULT;
	}
}
