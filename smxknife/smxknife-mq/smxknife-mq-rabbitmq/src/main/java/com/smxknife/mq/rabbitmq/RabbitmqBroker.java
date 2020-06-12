package com.smxknife.mq.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.smxknife.mq.api.Broker;
import com.smxknife.mq.api.DefaultBroker;

/**
 * @author smxknife
 * 2020/5/17
 */
public class RabbitmqBroker extends DefaultBroker {

	private boolean automaticRecoveryEnabled = true;
	private int networkRecoveryInterval = 10000;
	private int qos = 64;

	ConnectionFactory getConnectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(getHost());
		factory.setPort(getPort());
		factory.setUsername(getUsername());
		factory.setPassword(getPassword());
		factory.setAutomaticRecoveryEnabled(automaticRecoveryEnabled);
		factory.setNetworkRecoveryInterval(networkRecoveryInterval);
		return factory;
	}

	public boolean isAutomaticRecoveryEnabled() {
		return automaticRecoveryEnabled;
	}

	public void setAutomaticRecoveryEnabled(boolean automaticRecoveryEnabled) {
		this.automaticRecoveryEnabled = automaticRecoveryEnabled;
	}

	public int getNetworkRecoveryInterval() {
		return networkRecoveryInterval;
	}

	public void setNetworkRecoveryInterval(int networkRecoveryInterval) {
		this.networkRecoveryInterval = networkRecoveryInterval;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}
}
