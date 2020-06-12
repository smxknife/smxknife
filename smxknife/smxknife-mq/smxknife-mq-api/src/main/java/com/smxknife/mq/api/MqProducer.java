package com.smxknife.mq.api;

import java.util.Map;

/**
 * @author smxknife
 * 2020/5/15
 */
public abstract class MqProducer<T extends Broker> extends Mq<T> {

	public MqProducer(String name, T broker, WorkerExecutor workerExecutor) {
		super(name, broker, workerExecutor);
	}

	public final void publish(String destination, String path, String message, Map<String, Object> arguments) {
		getPreHandlers().forEach(handler -> handler.handle(destination, path, message, arguments));

		IdempotenceToken token = new IdempotenceToken();
		getIdempotenceHandler().ifPresent(handler -> token.copy(handler.createToken(broker.getIdempotencePrefix())));
		doPublish(destination, path, message, arguments, token);
	}

	protected abstract void doPublish(String destination, String path, String message, Map<String, Object> arguments, IdempotenceToken token);

}
