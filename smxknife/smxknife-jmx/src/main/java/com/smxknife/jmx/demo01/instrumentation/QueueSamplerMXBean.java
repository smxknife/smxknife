package com.smxknife.jmx.demo01.instrumentation;

public interface QueueSamplerMXBean {
	public QueueSample getQueueSample();
	public void clearQueue();
}
