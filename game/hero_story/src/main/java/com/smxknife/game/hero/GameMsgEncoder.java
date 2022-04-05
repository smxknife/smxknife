package com.smxknife.game.hero;

import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.handler.result.ResultHandler;
import com.smxknife.game.hero.handler.result.ResultHandlerRepository;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/6
 */
@Log4j2
public class GameMsgEncoder extends ChannelOutboundHandlerAdapter {

	private ResultHandlerRepository resultHandlerRepository;

	public GameMsgEncoder(ResultHandlerRepository resultHandlerRepository) {
		this.resultHandlerRepository = resultHandlerRepository;
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (null == msg || !(msg instanceof MsgWrapper)) {
			super.write(ctx, msg, promise);
			return;
		}

		final MsgWrapper msgWrapper = (MsgWrapper) msg;
		final Optional<ResultHandler> resultHandlerOptional = resultHandlerRepository.getHandler(msgWrapper.getMsgCode());
		if (!resultHandlerOptional.isPresent()) {
			log.error("无法识别消息类型：msgCode：{}", msgWrapper.getMsgCode());
			return;
		}
		final ResultHandler resultHandler = resultHandlerOptional.get();

		final byte[] bytes = resultHandler.encode(msgWrapper.getMessage());
		int msgCode = msgWrapper.getMsgCode().getNumber();

		final ByteBuf buffer = ctx.alloc().buffer();
		buffer.writeShort((short)0);
		buffer.writeShort((short)msgCode);
		buffer.writeBytes(bytes);

		BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buffer);
		super.write(ctx, frame, promise);

	}
}
