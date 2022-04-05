package com.smxknife.game.hero;

import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.handler.cmd.CmdHandlerRepository;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.log4j.Log4j2;

/**
 * @author smxknife
 * 2021/5/6
 */
@Log4j2
public class GameMsgDecoder extends ChannelInboundHandlerAdapter {

	private CmdHandlerRepository cmdHandlerRepository;

	public GameMsgDecoder(CmdHandlerRepository cmdHandlerRepository) {
		this.cmdHandlerRepository = cmdHandlerRepository;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (!(msg instanceof BinaryWebSocketFrame)) {
			return;
		}

		final BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) msg;
		final ByteBuf byteBuf = binaryWebSocketFrame.content();
		// byteBuf里面的数据格式 0, 0, 0, 0, 8, 1, 118...
		// 前2位代表消息长度
		// 3-4位代表消息编号
		byteBuf.readShort(); // 读取消息的长度
		final int msgCode = byteBuf.readShort(); // 读取消息编号

		// 拿到消息体
		byte[] msgBody = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(msgBody);

		this.cmdHandlerRepository.getHandler(GameMsgProtocol.MsgCode.forNumber(msgCode)).ifPresent(cmdHandler -> {
			final MsgWrapper msgWrapper = cmdHandler.decode(msgBody);
			if (null != msgWrapper) {
				ctx.fireChannelRead(msgWrapper);
			}
		});
	}
}
