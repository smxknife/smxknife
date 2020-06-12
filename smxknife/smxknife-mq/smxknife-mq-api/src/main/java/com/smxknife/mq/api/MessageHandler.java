package com.smxknife.mq.api;

import java.util.concurrent.CompletableFuture;

/**
 * @author smxknife
 * 2020/5/15
 */
public interface MessageHandler<T> {
	CompletableFuture handle(String destination, String path, T msg);
}
