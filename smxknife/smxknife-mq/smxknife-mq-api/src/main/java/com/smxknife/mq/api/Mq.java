package com.smxknife.mq.api;

/**
 * @author smxknife
 * 2020/5/18
 */
public abstract class Mq<T extends Broker> extends MqContext {

	protected String name;
	protected T broker;
	protected WorkerExecutor workerExecutor;

	public Mq(String name, T broker, WorkerExecutor workerExecutor) {
		this.broker = broker;
		this.workerExecutor = workerExecutor;
		this.name = name;
	}

	public void start() throws Exception {
		init();
	}

	protected abstract void init() throws Exception;
}
