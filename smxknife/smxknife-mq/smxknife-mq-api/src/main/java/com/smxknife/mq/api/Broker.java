package com.smxknife.mq.api;

/**
 * @author smxknife
 * 2020/5/15
 */
public interface Broker {
	String getHost();
	int getPort();
	String getUsername();
	String getPassword();
	String getIdempotencePrefix();
}
