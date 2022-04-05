package com.smxknife.game.hero;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.NonNull;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/7
 */
public enum Broadcaster {

	INSTANCE;

	private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 添加信道
	 * @param channel
	 */
	public void addChannel(@NonNull Channel channel) {
		channelGroup.add(channel);
	}

	/**
	 * 移除信道
	 * @param channel
	 */
	public void removeChannel(@NonNull Channel channel) {
		channelGroup.remove(channel);
	}

	/**
	 * 广播消息
	 * @param msg
	 */
	public void broadcast(Object msg) {
		if (Objects.isNull(msg)) {
			return;
		}
		channelGroup.writeAndFlush(msg);
	}
}
