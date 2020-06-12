package com.smxknife.rabbitmq.cluster;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/5/7
 */
public class Producer1 extends AbstractMQActuator {
	@Override
	protected void actuate(Channel channel) {
		try {
			channel.exchangeDeclare("exchange.producer_1", "direct", true, false, null);
			channel.queueDeclare("queue.queue_1", true, false, false, null);
			channel.queueBind("queue.queue_1", "exchange.producer_1", "binding.queue_1.producer_1");

			String body = "Hello, " + LocalDateTime.now();
			channel.basicPublish("exchange.producer_1", "binding.queue_1.producer_1", true, MessageProperties.PERSISTENT_TEXT_PLAIN, body.getBytes());
			channel.close();
		} catch (IOException|TimeoutException e) {
			e.printStackTrace();
		}
	}
}
