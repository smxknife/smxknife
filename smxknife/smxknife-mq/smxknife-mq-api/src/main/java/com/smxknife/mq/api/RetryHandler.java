package com.smxknife.mq.api;

/**
 * @author smxknife
 * 2020/5/18
 */
public interface RetryHandler {
	boolean enableRetry();
	int maxAttempts();
	boolean enableConfirm();
	int retryInterval();
}
