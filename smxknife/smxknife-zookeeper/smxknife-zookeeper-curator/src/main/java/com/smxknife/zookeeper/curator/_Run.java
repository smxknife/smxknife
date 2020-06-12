package com.smxknife.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author smxknife
 * 2019/12/3
 */
public class _Run {
	public static void main(String[] args) {
		String connectionString = "127.0.0.1";
		/**
		 * 随着重试次数增加重试时间间隔变大,指数倍增长baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1)))。
		 * 有两个构造方法
		 * baseSleepTimeMs初始sleep时间
		 * maxRetries最多重试几次
		 * maxSleepMs最大的重试时间
		 * 如果在最大重试次数内,根据公式计算出的睡眠时间超过了maxSleepMs，将打印warn级别日志,并使用最大sleep时间。
		 * 如果不指定maxSleepMs，那么maxSleepMs的默认值为Integer.MAX_VALUE。
		 */
		ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectionString, retry);
		curatorFramework.start();

		CuratorFramework curatorFramework1 = CuratorFrameworkFactory.newClient(connectionString, 60000, 15000, retry);
		curatorFramework1.start();

		CuratorFramework build = CuratorFrameworkFactory.builder().connectString(connectionString)
				.sessionTimeoutMs(60000)
				.connectionTimeoutMs(15000)
				.namespace("/node1")
				.retryPolicy(retry)
				.build();
		build.start();
	}
}
