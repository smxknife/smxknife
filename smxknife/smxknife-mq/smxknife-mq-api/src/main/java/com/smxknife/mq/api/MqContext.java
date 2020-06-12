package com.smxknife.mq.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/5/18
 */
class MqContext {

	private RetryHandler retryHandler;
	private IdempotenceHandler idempotenceHandler;
	private List<Handler> preHandlers = new ArrayList<>();
	private ProducerListener producerListener;
	private ConsumerListener consumerListener;

	public Optional<RetryHandler> getRetryHandler() {
		return Optional.ofNullable(retryHandler);
	}

	public void setRetryHandler(RetryHandler retryHandler) {
		this.retryHandler = retryHandler;
	}

	public Optional<IdempotenceHandler> getIdempotenceHandler() {
		return Optional.ofNullable(idempotenceHandler);
	}

	public void setIdempotenceHandler(IdempotenceHandler idempotenceHandler) {
		this.idempotenceHandler = idempotenceHandler;
	}

	public List<Handler> getPreHandlers() {
		return preHandlers;
	}

	public void setPreHandlers(List<Handler> preHandlers) {
		this.preHandlers = preHandlers;
	}

	public Optional<ProducerListener> getProducerListener() {
		return Optional.ofNullable(producerListener);
	}

	public void setProducerListener(ProducerListener producerListener) {
		this.producerListener = producerListener;
	}

	public Optional<ConsumerListener> getConsumerListener() {
		return Optional.ofNullable(consumerListener);
	}

	public void setConsumerListener(ConsumerListener consumerListener) {
		this.consumerListener = consumerListener;
	}
}
