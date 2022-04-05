package com.smxknife.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author smxknife
 * 2020/9/7
 */
public class _01_Single {
	public static void main(String[] args) {
		// 默认连接地址 127.0.0.1:6379
		// RedissonClient redisson = Redisson.create();

		Config config = new Config();
		config.useSingleServer().setAddress("myredisserver:6379");
		RedissonClient redisson = Redisson.create(config);
	}
}
