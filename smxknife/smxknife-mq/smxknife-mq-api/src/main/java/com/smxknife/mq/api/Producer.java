package com.smxknife.mq.api;

import java.util.Map;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Producer {

	private MqProducer mqProducer;
	public Producer(MqProducer mqProducer) throws Exception {
		this.mqProducer = mqProducer;
		this.mqProducer.start();
	}

	public void publish(String destination, String path, String message) {
		this.publish(destination, path, message, null);
	}

	public void publish(String destination, String path, String message, Map<String, Object> arguments) {
		mqProducer.publish(destination, path, message, arguments);
	}
}
