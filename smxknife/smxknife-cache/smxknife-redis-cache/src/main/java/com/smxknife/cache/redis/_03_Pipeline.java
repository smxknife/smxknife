package com.smxknife.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/7/22
 */
public class _03_Pipeline {
	public static void main(String[] args) {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			// 生产pipeline对象
			Pipeline pipelined = jedis.pipelined();

			pipelined.set("hello", "world");
			pipelined.set("nihao", "shijie");
			pipelined.del("hello");
			pipelined.del("nihao");


			// 执行
			//pipelined.sync();
			List<Object> objects = pipelined.syncAndReturnAll();
			System.out.println(objects);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(jedis)) {
				jedis.close();
			}
		}
	}
}
