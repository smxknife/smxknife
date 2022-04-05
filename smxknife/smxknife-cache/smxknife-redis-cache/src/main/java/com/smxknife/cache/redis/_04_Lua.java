package com.smxknife.cache.redis;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author smxknife
 * 2020/7/23
 */
public class _04_Lua {
	public static void main(String[] args) throws IOException {
		Jedis jedis = new Jedis("localhost", 6379);

		//eval(jedis);
		evalsha(jedis);
	}

	private static void eval(Jedis jedis) {
		String script = "return redis.call('get', KEYS[1])";
		Object eval = jedis.eval(script, 1, "hello");
		System.out.println(eval);

		eval = jedis.eval(script, 1, "counter");
		System.out.println(eval);
	}

	private static void evalsha(Jedis jedis) throws IOException {
		InputStream inputStream = _04_Lua.class.getClassLoader().getResourceAsStream("get.lua");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		// 将lua脚本加载到redis内存，返回sha
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String sha = jedis.scriptLoad(reader.readLine());

		// 根据sha执行lua脚本
		Object result = jedis.evalsha(sha, 1, "counter");
		System.out.println(result);
	}
}
