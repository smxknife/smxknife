package com.smxknife.game.hero.handler.result;

import com.smxknife.game.hero.protocol.GameMsgProtocol;

/**
 * @author smxknife
 * 2021/5/7
 */
public class WhoElseIsHereResultHandler extends AbstractResultHandler<GameMsgProtocol.WhoElseIsHereResult>{

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_RESULT;
	}
}
