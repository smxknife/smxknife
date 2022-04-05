package com.smxknife.game.hero;

import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.handler.cmd.CmdHandlerRepository;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/5
 */
@Log4j2
public class GameMessageHandler extends SimpleChannelInboundHandler<MsgWrapper> {

	private CmdHandlerRepository cmdHandlerRepository;

	public GameMessageHandler(@NonNull CmdHandlerRepository cmdHandlerRepository) {
		this.cmdHandlerRepository = cmdHandlerRepository;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 每增加一个用户添加到group中，为了实现消息的群发
		cmdHandlerRepository.getServerContext().getBroadcaster().addChannel(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// 当客户端离线时触发的函数
		super.handlerRemoved(ctx);
		final Channel channel = ctx.channel();
		cmdHandlerRepository.getServerContext().getBroadcaster().removeChannel(channel);
		final Integer userId = channel.attr(AttributeKey.<Integer>valueOf("userId")).get();
		if (Objects.isNull(userId)) {
			return;
		}
		cmdHandlerRepository.getServerContext().getUserManager().remove(userId);
		final GameMsgProtocol.UserQuitResult userQuitResult = GameMsgProtocol.UserQuitResult.newBuilder()
				.setQuitUserId(userId).build();

		cmdHandlerRepository.getServerContext().getBroadcaster()
				.broadcast(new MsgWrapper(GameMsgProtocol.MsgCode.USER_QUIT_RESULT, userQuitResult));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MsgWrapper msgWrapper) throws Exception {
		log.info("收到用户消息：{}", msgWrapper.getMessage());

		cmdHandlerRepository.handle(ctx, msgWrapper);
	}

}
