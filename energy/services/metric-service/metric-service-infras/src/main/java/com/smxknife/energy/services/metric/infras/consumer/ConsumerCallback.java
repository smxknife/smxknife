package com.smxknife.energy.services.metric.infras.consumer;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface ConsumerCallback {
	void onSuccess(String topic, int partition, long offset);

	void onFail(String topic, int partition, long offset);
}
