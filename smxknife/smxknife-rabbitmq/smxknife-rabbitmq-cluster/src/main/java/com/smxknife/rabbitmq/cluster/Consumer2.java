package com.smxknife.rabbitmq.cluster;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/5/7
 */
public class Consumer2 extends AbstractMQActuator {
	@Override
	protected void actuate(Channel channel) {
		try {
			channel.basicQos(64);

			DefaultConsumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					System.out.println("receive message: " + new String(body));
					System.out.println(consumerTag);
					System.out.println(envelope.getRoutingKey());
					System.out.println(envelope.getDeliveryTag());
					System.out.println(properties.getContentType());
					System.out.println("-----------------------");

					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			};

			channel.basicConsume("queue.queue_1", false, "c2", consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
