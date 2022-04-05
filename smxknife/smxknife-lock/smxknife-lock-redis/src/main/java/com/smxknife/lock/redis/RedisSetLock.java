package com.smxknife.lock.redis;

import com.smxknife.lock.BaseLock;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * @author smxknife
 * 2021/6/11
 */
@Deprecated
public class RedisSetLock extends BaseLock {

	private JedisPool jedisPool;
	private String lockName;
	private long expireMills;


	public void lock() {


		/**
		 * 方案2：
		 * set lock_name threadid ex 10 nx
		 * 加锁是没有问题的，
		 * 解锁：threadid == get lock_name 然后 del lock_name 不是原子的
		 */

		try (final Jedis jedis = jedisPool.getResource()) {
			while (true) {

				// 为了对可重入进行判断
				// 到那时这种方式是错误的，因为jedis.get返回的线程名，与下一步的判断不是一个原子操作，
				// 假如刚获得的name为本线程id，但是由于watchdog没有及时续约，

//				final String name = jedis.get(lockName);
//				if (name.equals(Thread.currentThread().getName())) {
//
//				}


				final String string = jedis.set(lockName, Thread.currentThread().getName(), SetParams.setParams().nx().px(expireMills));
				if ("OK".equals(string)) {
					// 加锁成功
					return;
				}
			}
		} catch (Exception e) {
			// redis连接异常，加锁失败
		}


	}

	@Deprecated
	public void unlock() {
		// 这里就是删除key的过程

		try(final Jedis jedis = jedisPool.getResource()) {
			/**
			 * 删除key
			 * 但是不能随便删除，不能删除别人的锁，所以需要判断一下key对应的值是否为当前的线程
			 * 但是，出现判读是否可重入就可能出问题，因为非原子的
			 */

			final String threadName = jedis.get(this.lockName);
			if (Thread.currentThread().getName().equals(threadName)) {
				jedis.del(this.lockName);
			}
		} catch (Exception e) {
			// jedis 连接获取异常，解锁失败
		}
	}
}
