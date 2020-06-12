package com.smxknife.mq.api.redis;

import com.smxknife.mq.api.IdempotenceHandler;
import com.smxknife.mq.api.IdempotenceToken;

import java.util.UUID;

/**
 * @author smxknife
 * 2020/5/18
 */
public class RedisIdempotenceHandler implements IdempotenceHandler {
	private JedisUtil jedisUtil;
	public RedisIdempotenceHandler(JedisUtil jedisUtil) {
		this.jedisUtil = jedisUtil;
	}

	@Override
	public IdempotenceToken createToken(String prefix) {
		UUID uuid = UUID.randomUUID();
		StringBuilder builder = new StringBuilder(prefix);
		builder.append(uuid.toString());
		String token = builder.toString();
		String set = jedisUtil.set(token, token);
		IdempotenceToken idempotenceToken = new IdempotenceToken();
		idempotenceToken.setEnable(true);
		idempotenceToken.setKey(token);
		idempotenceToken.setToken(token);
		return idempotenceToken;
	}

	@Override
	public IdempotenceToken checkToken(String tokenKey) {
		if (!jedisUtil.exists(tokenKey)) {
			throw new RuntimeException(String.format("IdempotenceToken[%s] is not exist!!", tokenKey));
		}
		Long del = jedisUtil.del(tokenKey);
		if (del < 0) {
			throw new RuntimeException(String.format("IdempotenceToken[%s] is not exist!!", tokenKey));
		}
		IdempotenceToken token = new IdempotenceToken();
		token.setEnable(true);
		token.setKey(tokenKey);
		return token;
	}

	@Override
	public void deleteToken(String tokenKey) {
		jedisUtil.del(tokenKey);
	}
}
