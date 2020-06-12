package com.smxknife.mq.api;

import java.util.List;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/5/15
 */
public interface ProducerListener extends MqListener {

	void publishSuccess(Object... args);
	void publishFail(Object... args);
	void publishReturn(byte[] data);
	void unexpectException(Throwable e);
}
