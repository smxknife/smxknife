package com.smxknife.rabbitmq.single;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/4/25
 */
public class RabbitProducerAlternateExchange {

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
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("alternate-exchange", "myAe");
		channel.exchangeDeclare("normalExchange", "direct", true, false, argMap);

		// 声明一个AlternateExchange
		channel.exchangeDeclare("myAe", "fanout", true, false, null);

		// 创建一个持久化、非排他的、非自动删除的队列
		channel.queueDeclare("normalQueue", true, false, false, null);
		// 将队列和交换器通过路由绑定
		channel.queueBind("normalQueue", "normalExchange", "normalKey");
		channel.queueDeclare("unroutedQueue", true, false, false, null);
		channel.queueBind("unroutedQueue", "myAe", "");
		// 发送一条持久化消息
		String message = " Mandatory No RoutingKey";

		// TODO mandatory设置为true
		channel.basicPublish("normalQueue", "", true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

		// 关闭资源
		TimeUnit.SECONDS.sleep(50);
		channel.close();
		connection.close();
	}

}
