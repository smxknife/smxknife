package com.smxknife.lock.redis;

import com.smxknife.lock.BaseLock;
import redis.clients.jedis.Jedis;

/**
 * @author smxknife
 * 2021/6/11
 */
public class RedisLock extends BaseLock {

	private Jedis jedis;


	public void lock() {
		/**
		 * 方案1：
		 * 加锁 setnx + expire
		 * 这种方案，setnx 和 expire不是原子的
 		 */

		/**
		 * 方案2：
		 * set lock_name threadid ex 10 nx
		 * 加锁是没有问题的，
		 * 解锁：threadid == get lock_name 然后 del lock_name 不是原子的
		 */

		/**
		 * 方案3：
		 * 使用lua脚本
		 */

	}
}
