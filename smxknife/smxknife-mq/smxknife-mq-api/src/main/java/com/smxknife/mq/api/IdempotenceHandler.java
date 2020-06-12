package com.smxknife.mq.api;

/**
 * @author smxknife
 * 2020/5/17
 */
public interface IdempotenceHandler {

	IdempotenceToken createToken(String prefix);
	IdempotenceToken checkToken(String tokenKey);
	void deleteToken(String tokenKey);

}
