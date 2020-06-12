package com.smxknife.mq.api;

import java.util.*;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Consumer {

	private MqConsumer mqConsumer;
	public Consumer(MqConsumer mqConsumer) throws Exception {
		this.mqConsumer = mqConsumer;
		this.mqConsumer.start();
	}

	public void subscribe(String destination, String path, MessageHandler handler) {
		this.subscribe(destination, Arrays.asList(path), handler);
	}

	public void subscribe(String destination, List<String> paths, MessageHandler handler) {
		this.subscribe(destination, paths, null, handler);
	}
	public void subscribe(String destination, List<String> paths, Map<String, Object> arguments, MessageHandler handler) {
		mqConsumer.subscribe(destination, paths, arguments, handler);
	}
}
