package com.smxknife.zookeeper.curator.v5.demo01;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/9/22
 */
public class ListenerDemo {
	public static void main(String[] args) throws InterruptedException {
		CuratorFramework framework = CuratorFrameworkFactory.builder().connectString("localhost:2191")
				.namespace("demo01")
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		framework.start();

		CuratorCache cache = CuratorCache.build(framework, "/");
		cache.start();

		cache.listenable().addListener(new NodeListener());


		TimeUnit.HOURS.sleep(1);


	}
}
