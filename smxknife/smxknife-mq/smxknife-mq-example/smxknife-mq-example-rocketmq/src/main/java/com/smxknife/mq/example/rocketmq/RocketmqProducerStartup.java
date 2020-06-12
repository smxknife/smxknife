package com.smxknife.mq.example.rocketmq;

import com.smxknife.mq.api.MqProducer;
import com.smxknife.mq.api.Producer;
import com.smxknife.mq.api.WorkerExecutor;
import com.smxknife.mq.api.redis.JedisUtil;
import com.smxknife.mq.rocketmq.RocketMqBroker;
import com.smxknife.mq.rocketmq.RocketMqProducer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author smxknife
 * 2020/5/18
 */
public class RocketmqProducerStartup {
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

		MqProducer mqProducer = new RocketMqProducer("hhGrp", rocketMqBroker, workerExecutor);
		Producer producer = new Producer(mqProducer);
		producer.publish("exchange", "mytag", "这是测试");
	}
}
