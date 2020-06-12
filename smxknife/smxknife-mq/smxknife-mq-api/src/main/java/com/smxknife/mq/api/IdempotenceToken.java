package com.smxknife.mq.api;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/5/18
 */
public class IdempotenceToken {
	private boolean enable = false;
	private String key;
	private String token;

	public IdempotenceToken copy(IdempotenceToken token) {
		Objects.requireNonNull(token);
		this.enable = token.enable;
		this.key = token.key;
		this.token = token.token;
		return this;
	}

	public boolean checkSuccess() {
		if (!enable) {
			return true;
		}
		return Objects.nonNull(this.key);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
