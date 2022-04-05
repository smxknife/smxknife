package com.smxknife.cache.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author smxknife
 * 2020/7/22
 */
public class _01_Jedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> keys = jedis.keys("*");
		System.out.println(keys);
		String val = jedis.get("a");
		System.out.println(val);

	}
}
