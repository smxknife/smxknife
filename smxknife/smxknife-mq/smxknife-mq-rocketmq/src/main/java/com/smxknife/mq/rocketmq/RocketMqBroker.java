package com.smxknife.mq.rocketmq;

import com.smxknife.mq.api.Broker;
import com.smxknife.mq.api.DefaultBroker;

/**
 * @author smxknife
 * 2020/5/18
 */
public class RocketMqBroker extends DefaultBroker {

	public String getNamesrvAddr() {
		return String.format("%s:%s", getHost(), getPort());
	}
}
