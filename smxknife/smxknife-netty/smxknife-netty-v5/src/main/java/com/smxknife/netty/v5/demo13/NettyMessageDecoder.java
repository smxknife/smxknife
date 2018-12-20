package com.smxknife.netty.v5.demo13;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2018-12-11
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

	private NettyMarshallingDecoder marshallingDecoder;

	private String name;

	public NettyMessageDecoder setName(String name) {
		this.name = name;
		return this;
	}

	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
		marshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();
	}

	public Object decode(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
		System.out.println(String.format(">>>> %s [ name : %s ] decode.", this.getClass().getSimpleName(), name));
		if ("server".equals(name)) {
			System.out.println("debug server");
		} else {
			System.out.println("debug client");
		}
		ByteBuf frame = (ByteBuf) super.decode(ctx, byteBuf);
		if (frame == null) {
			return null;
		}
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		header.setCrcCode(frame.readInt());
		header.setLength(frame.readInt());
		header.setSessionID(frame.readLong());
		header.setType(frame.readByte());
		header.setPriority(frame.readByte());

		int size = frame.readInt();
		if (size > 0) {
			Map<String, Object> attachments = new HashMap<>(size);
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for (int i = 0; i < size; i++) {
				keySize = frame.readInt();
				keyArray = new byte[keySize];
				frame.readBytes(keyArray);
				key = new String(keyArray, "UTF-8");
				attachments.put(key, marshallingDecoder.decode(ctx, frame));
			}
			key = null;
			keyArray = null;
			header.setAttachment(attachments);
		}

		if (frame.readableBytes() > 4) {
			msg.setBody(marshallingDecoder.decode(ctx, frame));
		}
		msg.setHeader(header);
		return msg;
	}
}
