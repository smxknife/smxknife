package com.smxknife.mq.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.smxknife.mq.api.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Map;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/5/18
 */
@Slf4j
public class RocketMqProducer extends MqProducer<RocketMqBroker> {

	private DefaultMQProducer producer;

	public RocketMqProducer(String name, RocketMqBroker broker, WorkerExecutor workerExecutor) {
		super(name, broker, workerExecutor);
	}

	@Override
	protected void doPublish(String destination, String path, String message, Map<String, Object> arguments, IdempotenceToken token) {
		Message msg = new Message(destination, path, message.getBytes());
		if (token.isEnable()) {
			msg.putUserProperty(Constant.MESSAGE_HEADER_TOKEN_KEY, token.getKey());
		}

		try {
			Optional<ProducerListener> producerListener = getProducerListener();
			producer.send(msg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					log.info("topic {} 数据发送成功", destination);
					producerListener.ifPresent(listener -> listener.publishSuccess(sendResult));
				}

				@Override
				public void onException(Throwable throwable) {
					log.warn("topic {} 数据发送失败, error : {}", destination, throwable.getMessage());
					producerListener.ifPresent(listener -> listener.unexpectException(throwable));
				}
			});
		} catch (MQClientException|RemotingException|InterruptedException e) {
			if (log.isDebugEnabled()) {
				log.error(e.getMessage(), e);
			} else {
				log.error(e.getMessage());
			}
		}

	}

	@Override
	protected void init() throws Exception {
		producer = new DefaultMQProducer(name);
		producer.setNamesrvAddr(broker.getNamesrvAddr());
		producer.start();
	}
}
