package com.smxknife.cache.memcached.demo01;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.GetFuture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

/**
 * @author smxknife
 * 2020/6/20
 */
public class Main {
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		InetSocketAddress address = new InetSocketAddress(11211);
		MemcachedClient client = new MemcachedClient(address);

		String userKey = "test_user";
		System.out.println("get ----------------------");
		Object o = getFromCache(client, userKey);
		System.out.println(o);
		System.out.println("get finish ---------------");

		User user1 = createUser(1, "test", 10);
		client.add(userKey, 30, user1);
		System.out.println("add user1 to cache");

		System.out.println("get ----------------------");
		Object o2 = getFromCache(client, userKey);
		System.out.println(o2);
		System.out.println("get finish ---------------");

		System.out.println("async get ----------------");
		GetFuture<Object> getFuture = getFromCacheAsync(client, userKey);
		if (getFuture.isDone()) {
			System.out.println(getFuture.get());
		}
		System.out.println("async get finish ---------");

		System.out.println(getFuture.get());

	}

	private static User createUser(int id, String name, int age) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		return user;
	}

	private static Object getFromCache(MemcachedClient client, String key) {
		return client.get(key);
	}

	private static GetFuture<Object> getFromCacheAsync(MemcachedClient client, String key) {
		return client.asyncGet(key);
	}
}
