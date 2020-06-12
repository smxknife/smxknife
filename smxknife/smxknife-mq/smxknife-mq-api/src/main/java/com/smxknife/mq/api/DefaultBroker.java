package com.smxknife.mq.api;

/**
 * @author smxknife
 * 2020/5/18
 */
public class DefaultBroker implements Broker {
	private String host;
	private int port;
	private String username;
	private String password;
	private String idempotencePrefix = "idempotencePrefix";

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getIdempotencePrefix() {
		return idempotencePrefix;
	}

	public void setIdempotencePrefix(String idempotencePrefix) {
		this.idempotencePrefix = idempotencePrefix;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
