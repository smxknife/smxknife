package com.smxknife.rabbitmq.cluster;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/5/7
 */
public abstract class AbstractMQActuator implements Runnable {
	@Override
	public final void run() {
		Optional<Channel> optional = channel();
		optional.ifPresent(channel -> {
			actuate(channel);
		});
		optional.orElseThrow(() -> new RuntimeException("Channel is not exist"));
	}

	protected abstract void actuate(Channel channel);

	private Optional<Channel> channel() {
		Connection connection = RabbitmqConnectionFactory.getConnection();
		Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(channel);
	}
}
