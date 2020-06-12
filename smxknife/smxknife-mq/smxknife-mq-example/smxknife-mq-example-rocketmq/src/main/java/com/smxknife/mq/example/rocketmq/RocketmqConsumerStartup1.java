package com.smxknife.mq.example.rocketmq;

import com.smxknife.mq.api.Consumer;
import com.smxknife.mq.api.MessageHandler;
import com.smxknife.mq.api.MqConsumer;
import com.smxknife.mq.api.WorkerExecutor;
import com.smxknife.mq.api.redis.JedisUtil;
import com.smxknife.mq.rocketmq.RocketMqBroker;
import com.smxknife.mq.rocketmq.RocketMqConsumer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CompletableFuture;

/**
 * @author smxknife
 * 2020/5/18
 */
public class RocketmqConsumerStartup1 {
	public static void main(String[] args) throws Exception {
		RocketMqBroker rocketMqBroker = new RocketMqBroker();
		rocketMqBroker.setHost("192.168.32.215");
		rocketMqBroker.setPort(9876);

		// redis
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

		// jedisUtil
		JedisUtil jedisUtil = new JedisUtil(jedisPool);

		// WorkerExecutor
		WorkerExecutor workerExecutor = WorkerExecutor.newCachedThreadPool("producerWorkerThreadPool");

		MqConsumer mqConsumer = new RocketMqConsumer("testGrp", rocketMqBroker, workerExecutor);
		Consumer consumer = new Consumer(mqConsumer);
		consumer.subscribe("kernel", "data", new MessageHandler<String>() {
			@Override
			public CompletableFuture handle(String destination, String path, String msg) {
				return CompletableFuture.runAsync(() -> {
					System.out.println(destination + ", " + path + ", " + msg);
				});
			}
		});
	}
}
