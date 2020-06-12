package com.smxknife.mq.example.rabbitmq;

import com.smxknife.mq.api.MqProducer;
import com.smxknife.mq.api.Producer;
import com.smxknife.mq.api.WorkerExecutor;
import com.smxknife.mq.api.redis.JedisUtil;
import com.smxknife.mq.rabbitmq.RabbitMqProducer;
import com.smxknife.mq.rabbitmq.RabbitmqBroker;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author smxknife
 * 2020/5/18
 */
public class ProducerStartup {
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

		MqProducer mqProducer = new RabbitMqProducer(rabbitmqBroker, workerExecutor);
		Producer producer = new Producer(mqProducer);
		producer.publish("exchange.test", "test.mytest", "这是测试");

	}
}
