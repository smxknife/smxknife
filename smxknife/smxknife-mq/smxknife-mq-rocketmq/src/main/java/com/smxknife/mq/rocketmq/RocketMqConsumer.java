package com.smxknife.mq.rocketmq;

import com.smxknife.mq.api.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/5/18
 */
@Slf4j
public class RocketMqConsumer extends MqConsumer<RocketMqBroker> {

	private DefaultMQPushConsumer consumer;

	public RocketMqConsumer(String name, RocketMqBroker broker, WorkerExecutor workerExecutor) {
		super(name, broker, workerExecutor);
	}

	@Override
	public void subscribe(String destination, List<String> tags, Map<String, Object> arguments, MessageHandler handler) {
		try {
			Object messageModel = Objects.nonNull(arguments) ? arguments.get("MessageModel") : null;
			if (Objects.nonNull(messageModel)) {
				if (MessageModel.BROADCASTING.getModeCN().equalsIgnoreCase(String.valueOf(messageModel))) {
					consumer.setMessageModel(MessageModel.BROADCASTING);
				}
			}
			consumer.subscribe(destination, tags.get(0));
			consumer.setConsumeFromWhere(
					ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

			IdempotenceToken token = new IdempotenceToken();

			consumer.registerMessageListener(
					new MessageListenerConcurrently() {
						@Override
						public ConsumeConcurrentlyStatus consumeMessage(
								List<MessageExt> list,
								ConsumeConcurrentlyContext Context) {
							Message msg = list.get(0);
							String topic = msg.getTopic();
							String tags = msg.getTags();
							byte[] body = msg.getBody();
							String data = new String(body);
							String tokenKey = msg.getProperties().get(Constant.MESSAGE_HEADER_TOKEN_KEY);
							getIdempotenceHandler().ifPresent(handler -> token.copy(handler.checkToken(tokenKey)));

							if (!token.checkSuccess()) {
								log.warn("Skipped!!! {} receive duplicate message from exchange = {}, routingKey = {}, msg = {}", name, topic, tags, data);
								return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
							}

							log.info("{} receive message from topic = {}, tags = {}, msg = {} ", name, topic, tags, data);
							handler.handle(topic, tags, data);
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
			);
			consumer.start();
		} catch (MQClientException e) {
			log.error(e.getErrorMessage(), e);
		}

	}

	@Override
	protected void init() throws Exception {
		consumer = new DefaultMQPushConsumer(name);
		consumer.setNamesrvAddr(broker.getNamesrvAddr());
	}
}
