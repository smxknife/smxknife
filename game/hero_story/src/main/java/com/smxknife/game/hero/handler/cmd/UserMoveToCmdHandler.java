package com.smxknife.game.hero.handler.cmd;

import com.google.protobuf.InvalidProtocolBufferException;
import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/7
 */
public class UserMoveToCmdHandler extends AbstractCmdHandler<GameMsgProtocol.UserMoveToCmd> {

	@Override
	public void doHandle(ServerContext serverContext, ChannelHandlerContext ctx, GameMsgProtocol.UserMoveToCmd userMoveToCmd) {
		final Integer userId = (Integer)ctx.channel().attr(AttributeKey.valueOf("userId")).get();
		if (Objects.isNull(userId)) {
			return;
		}

		GameMsgProtocol.UserMoveToResult userMoveToResult = GameMsgProtocol.UserMoveToResult.newBuilder()
				.setMoveUserId(userId).setMoveToPosX(userMoveToCmd.getMoveToPosX())
				.setMoveToPosY(userMoveToCmd.getMoveToPosY()).build();

		serverContext.getBroadcaster()
				.broadcast(new MsgWrapper(GameMsgProtocol.MsgCode.USER_MOVE_TO_RESULT, userMoveToResult));
	}

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.USER_MOVE_TO_CMD;
	}

	@Override
	public MsgWrapper decode(byte[] bytes) {
		try {
			return new MsgWrapper(code(), GameMsgProtocol.UserMoveToCmd.newBuilder().mergeFrom(bytes).build());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return null;
	}
}
