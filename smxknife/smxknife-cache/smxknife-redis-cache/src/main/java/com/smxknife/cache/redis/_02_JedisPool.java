package com.smxknife.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/7/22
 */
public class _02_JedisPool {
	public static void main(String[] args) {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
		Jedis jedis = null;
		try {
			// 1. 从连接池中获取jedis对象
			jedis = jedisPool.getResource();
			// 2. 执行操作
			Long del = jedis.del("a");
			System.out.println(del);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(jedis)) {
				jedis.close();
			}
		}
	}
}
