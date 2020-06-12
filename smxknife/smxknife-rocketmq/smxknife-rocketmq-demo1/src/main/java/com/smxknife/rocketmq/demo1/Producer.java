package com.smxknife.rocketmq.demo1;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Producer {
	public static void main(String[] args) {
		DefaultMQProducer producer = new DefaultMQProducer("MyGrp");
		producer.setNamesrvAddr("localhost:9876");
		try {
			producer.start();
			Message msg = new Message("push_topic", "push", "1", "Just for test1".getBytes());
			SendResult result = producer.send(msg);
			System.out.println("id:" + result.getMsgId() +
					" result:" + result.getSendStatus());

			msg = new Message("push_topic",
					"push",
					"2",
					"Just for test2.".getBytes());
			result = producer.send(msg);
			System.out.println("id:" + result.getMsgId() +
					" result:" + result.getSendStatus());

			msg = new Message("push_topic",
					"push",
					"1",
					"Just for test3.".getBytes());
			result = producer.send(msg);
			System.out.println("id:" + result.getMsgId() +
					" result:" + result.getSendStatus());
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
