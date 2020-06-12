package com.smxknife.rabbitmq.single;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/4/25
 */
public class RabbitConsumerNoRoutingKey {

	private static final String IP_ADDRESS = "localhost";
	private static final int PORT = 5672;

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

		Address[] addresses = new Address[] {
				new Address(IP_ADDRESS, PORT)
		};

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("root");
		factory.setPassword("root");
		Connection connection = factory.newConnection(addresses);
		Channel channel = connection.createChannel();
		// 设置客户端最多接收未被ack的消息个数
		channel.basicQos(64);

		DefaultConsumer consumer1 = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("receive message: " + new String(body));
				System.out.println(consumerTag);
				System.out.println(envelope.getRoutingKey());
				System.out.println(envelope.getDeliveryTag());
				System.out.println(properties.getContentType());
				System.out.println("-----------------------");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};

		channel.basicConsume("unroutedQueue", false, "c1", consumer1);
		TimeUnit.SECONDS.sleep(5);
		// 关闭资源
		channel.close();
		connection.close();
	}

}
