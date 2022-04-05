package com.smxknife.game.hero.handler.cmd;

import com.google.protobuf.InvalidProtocolBufferException;
import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import com.smxknife.game.hero.user.UserManager;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/7
 */
public class WhoElseIsHereCmdHandler extends AbstractCmdHandler<GameMsgProtocol.WhoElseIsHereCmd> {

	@Override
	public GameMsgProtocol.MsgCode code() {
		return GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_CMD;
	}

	@Override
	public MsgWrapper decode(byte[] bytes) {
		try {
			return new MsgWrapper(code(), GameMsgProtocol.WhoElseIsHereCmd.newBuilder().mergeFrom(bytes).build());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doHandle(ServerContext serverContext, ChannelHandlerContext ctx, GameMsgProtocol.WhoElseIsHereCmd msg) {
		final UserManager userManager = serverContext.getUserManager();
		final List<GameMsgProtocol.WhoElseIsHereResult.UserInfo> userInfos = Arrays.stream(userManager.listAll())
				.filter(Objects::nonNull)
				.map(user -> GameMsgProtocol.WhoElseIsHereResult.UserInfo
						.newBuilder().setUserId(user.getUserId()).setHeroAvatar(user.getHeroAvatar()).build())
				.collect(Collectors.toList());
		final GameMsgProtocol.WhoElseIsHereResult whoElseIsHereResult = GameMsgProtocol.WhoElseIsHereResult
				.newBuilder().addAllUserInfo(userInfos).build();

		ctx.writeAndFlush(new MsgWrapper(GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_RESULT, whoElseIsHereResult));
	}
}
