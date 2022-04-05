package com.smxknife.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/8/11
 */
public class _05_Sentinel {
	public static void main(String[] args) {
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",
				new HashSet<>(Arrays.asList("62.234.100.39:26379", "62.234.100.39:26380", "62.234.100.39:26381")));

		Jedis jedis = null;
		try {
			jedis = sentinelPool.getResource();
			String abc = jedis.get("abc");
			System.out.println(abc);
			String set = jedis.set("abc", "def");
			System.out.println(set);
			abc = jedis.get("abc");
			System.out.println(abc);
		} finally {
			if (Objects.nonNull(jedis)) {
				jedis.close();
			}
		}
	}
}
