package com.smxknife.rocketmq.demo1;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Consumer {
	public static void main(String[] args) {
		new Thread(() -> data()).start();
		new Thread(() -> metric()).start();
	}

	private static void metric() {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MyPush1");
		consumer.setNamesrvAddr("192.168.32.215:9876");

		try {
			//订阅push_topic下Tag为push的消息
			consumer.subscribe("mass_metric", "test");
			//程序第一次启动从消息队列头取数据
			consumer.setConsumeFromWhere(
					ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

			consumer.registerMessageListener(
					new MessageListenerConcurrently() {
						@Override
						public ConsumeConcurrentlyStatus consumeMessage(
								List<MessageExt> list,
								ConsumeConcurrentlyContext Context) {
							Message msg = list.get(0);
							System.out.println(msg.toString());
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
			);
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}

	private static void data() {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MyPush");
		consumer.setNamesrvAddr("192.168.32.215:9876");

		try {
			//订阅push_topic下Tag为push的消息
			consumer.subscribe("mass_data", "");
			//程序第一次启动从消息队列头取数据
			consumer.setConsumeFromWhere(
					ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

			consumer.registerMessageListener(
					new MessageListenerConcurrently() {
						@Override
						public ConsumeConcurrentlyStatus consumeMessage(
								List<MessageExt> list,
								ConsumeConcurrentlyContext Context) {
							Message msg = list.get(0);
							System.out.println(msg.toString());
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
			);
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
}
