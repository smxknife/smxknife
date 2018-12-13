package com.smxknife.netty.demo13;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Netty消息编码类
 * @author smxknife
 * 2018-12-11
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

	NettyMarshallingEncoder nettyMarshallingEncoder;

	private String name;

	public NettyMessageEncoder setName(String name) {
		this.name = name;
		return this;
	}

	public NettyMessageEncoder() {
		this.nettyMarshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
	}

	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage msg, List<Object> list) throws Exception {
		System.out.println(String.format(">>>> %s [ name : %s ] encode.", this.getClass().getSimpleName(), name));
		if ("server".equals(name)) {
			System.out.println("debug server");
		} else {
			System.out.println("debug client");
		}
		if (msg == null || msg.getHeader() == null) {
			throw new Exception("the encode message is null");
		}
		ByteBuf byteBuf = Unpooled.buffer();
		byteBuf.writeInt(msg.getHeader().getCrcCode());
		byteBuf.writeInt(msg.getHeader().getLength());
		byteBuf.writeLong(msg.getHeader().getSessionID());
		byteBuf.writeByte(msg.getHeader().getType());
		byteBuf.writeByte(msg.getHeader().getPriority());
		byteBuf.writeInt(msg.getHeader().getAttachment().size());

		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for (Map.Entry<String, Object> entry : msg.getHeader().getAttachment().entrySet()) {
			key = entry.getKey();
			keyArray = key.getBytes("UTF-8");
			byteBuf.writeInt(keyArray.length);
			byteBuf.writeBytes(keyArray);
			value = entry.getValue();
			nettyMarshallingEncoder.encode(channelHandlerContext, value, byteBuf);
		}
		key = null;
		keyArray = null;
		value = null;
		if (Objects.nonNull(msg.getBody())) {
			nettyMarshallingEncoder.encode(channelHandlerContext, msg.getBody(), byteBuf);
		} else {
			byteBuf.writeInt(0);
		}
		// 写入消息的总长度
		byteBuf.setInt(4, byteBuf.readableBytes());
		list.add(byteBuf);
	}
}
