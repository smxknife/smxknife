package com.smxknife.game.hero.handler.cmd;

import com.google.protobuf.InvalidProtocolBufferException;
import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import com.smxknife.game.hero.user.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * @author smxknife
 * 2021/5/7
 */
public class UserEntryCmdHandler extends AbstractCmdHandler<GameMsgProtocol.UserEntryCmd> {

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.USER_ENTRY_CMD;
	}

	@Override
	public MsgWrapper decode(byte[] bytes) {
		try {
			return new MsgWrapper(code(),
					GameMsgProtocol.UserEntryCmd.newBuilder().mergeFrom(bytes).build());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doHandle(ServerContext serverContext, ChannelHandlerContext ctx, GameMsgProtocol.UserEntryCmd cmd) {
		final int userId = cmd.getUserId();
		final String heroAvatar = cmd.getHeroAvatar();

		serverContext.getUserManager().add(new User(userId, heroAvatar));

		// 将userId附着到channel
		ctx.channel().attr(AttributeKey.valueOf("userId")).set(userId);

		final GameMsgProtocol.UserEntryResult userEntryResult = GameMsgProtocol.UserEntryResult.newBuilder()
				.setUserId(userId)
				.setHeroAvatar(heroAvatar)
				.build();

		serverContext.getBroadcaster()
				.broadcast(new MsgWrapper(GameMsgProtocol.MsgCode.USER_ENTRY_RESULT, userEntryResult));
	}
}
