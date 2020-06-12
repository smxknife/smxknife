package com.smxknife.rabbitmq.single;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/4/25
 */
public class RabbitProducerNoRoutingKey {
	private static final String EXCHANGE_NAME = "exchange_demo";
	private static final String ROUTING_KEY = "routingkey_demo_no";
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
		Channel channel = connection.createChannel();
		// 创建一个同一片"direct"，持久化的、非自动删除的交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
		// 创建一个持久化、非排他的、非自动删除的队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		// 将队列和交换器通过路由绑定
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
		// 发送一条持久化消息
		String message = " Mandatory No RoutingKey";

		// TODO mandatory设置为true
		channel.basicPublish(EXCHANGE_NAME, "", true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode,
			                         String replyText,
			                         String exchange,
			                         String routingKey,
			                         AMQP.BasicProperties properties,
			                         byte[] body) throws IOException {
				System.out.println("replyCode = " + replyCode);
				System.out.println("replyText = " + replyText);
				System.out.println("exchange = " + exchange);
				System.out.println("routingKey = " + routingKey);
				System.out.println("routingKey = " + routingKey);
				String message = new String(body);
				System.out.println("Basic.Return 返回的结果是：" + message);
			}
		});
		// 关闭资源
		TimeUnit.SECONDS.sleep(5);
		channel.close();
		connection.close();
	}

}
