package com.smxknife.mq.api;

import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/5/15
 */
public abstract class MqConsumer<T extends Broker> extends Mq<T> {

	public MqConsumer(String name, T broker, WorkerExecutor workerExecutor) {
		super(name, broker, workerExecutor);
	}

	public abstract void subscribe(String destination, List<String> paths, Map<String, Object> arguments, MessageHandler handler);
}
