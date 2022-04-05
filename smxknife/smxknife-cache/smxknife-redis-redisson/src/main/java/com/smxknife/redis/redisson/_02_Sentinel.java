package com.smxknife.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author smxknife
 * 2020/9/7
 */
public class _02_Sentinel {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSentinelServers()
				.setMasterName("mymaster")
				//可以用"rediss://"来启用SSL连接
				.addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
				.addSentinelAddress("127.0.0.1:26319");

		RedissonClient redisson = Redisson.create(config);
	}
}
