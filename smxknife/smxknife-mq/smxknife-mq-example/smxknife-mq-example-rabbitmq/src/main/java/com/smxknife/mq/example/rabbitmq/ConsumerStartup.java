package com.smxknife.mq.example.rabbitmq;

import com.smxknife.mq.api.*;
import com.smxknife.mq.api.redis.JedisUtil;
import com.smxknife.mq.rabbitmq.RabbitMqConsumer;
import com.smxknife.mq.rabbitmq.RabbitMqProducer;
import com.smxknife.mq.rabbitmq.RabbitmqBroker;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CompletableFuture;

/**
 * @author smxknife
 * 2020/5/18
 */
public class ConsumerStartup {
	public static void main(String[] args) throws Exception {
		// broker
		RabbitmqBroker rabbitmqBroker = new RabbitmqBroker();
		rabbitmqBroker.setHost("softmarket.site");
		rabbitmqBroker.setPort(6760);
		rabbitmqBroker.setUsername("root");
		rabbitmqBroker.setPassword("smxknife1");

		// redis
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

		// jedisUtil
		JedisUtil jedisUtil = new JedisUtil(jedisPool);

		// WorkerExecutor
		WorkerExecutor workerExecutor = WorkerExecutor.newCachedThreadPool("producerWorkerThreadPool");

		MqConsumer mqConsumer = new RabbitMqConsumer("queue.test", rabbitmqBroker, workerExecutor);
		Consumer consumer = new Consumer(mqConsumer);
		consumer.subscribe("exchange.test", "test.mytest", new MessageHandler<String>() {
			@Override
			public CompletableFuture handle(String destination, String path, String msg) {
				return CompletableFuture.runAsync(() -> {
					System.out.println(destination + ", " + path + ", " + msg);
				});
			}
		});

	}
}
