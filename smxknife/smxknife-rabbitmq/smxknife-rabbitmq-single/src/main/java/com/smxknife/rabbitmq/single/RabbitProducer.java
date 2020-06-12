package com.smxknife.rabbitmq.single;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/4/25
 */
public class RabbitProducer {
	private static final String EXCHANGE_NAME = "exchange_demo";
	private static final String ROUTING_KEY = "routingkey_demo";
	private static final String QUEUE_NAME = "queue_demo";
	private static final String IP_ADDRESS = "localhost";
	private static final int PORT = 5672;

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(IP_ADDRESS);
		factory.setPort(PORT);
		factory.setUsername("root");
		factory.setPassword("root");
		Connection connection = factory.newConnection();

		Optional<Channel> optionalChannel = connection.openChannel();
		Channel channel_1 = optionalChannel.get();
		System.out.println(channel_1.getChannelNumber());

		Channel channel = connection.createChannel();
		System.out.println(channel.getChannelNumber());

		optionalChannel = connection.openChannel();
		Channel channel_2 = optionalChannel.get();
		System.out.println(channel_1.getChannelNumber());

		// 创建一个同一片"direct"，持久化的、非自动删除的交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
		// 创建一个持久化、非排他的、非自动删除的队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		// 将队列和交换器通过路由绑定
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

		channel.confirmSelect();
		channel.addConfirmListener(new ConfirmListener() {
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("channel");
			}

			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("channel");
			}
		});

		channel_1.confirmSelect();
		channel_1.addConfirmListener(new ConfirmListener() {
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("channel_1");
			}

			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("channel_1");
			}
		});




		// 发送一条持久化消息
		String message = "Hello World";
		channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

		TimeUnit.SECONDS.sleep(10);
		// 关闭资源
		channel.close();
		connection.close();
	}

}
