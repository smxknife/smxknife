package com.smxknife.rabbitmq.cluster;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/5/6
 */
public enum RabbitmqConnectionFactory {
	INS;
	// TODO 这是一个不好的实现，不能把Connection放在ThreadLocal
	private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>() {
		private Connection connection = null;
		@Override
		protected Connection initialValue() {
			if (Objects.nonNull(connection)) {
				return connection;
			}
			try {
				RabbitmqProperties.loadProperties();
				String host = RabbitmqProperties.getHost();
				int port = RabbitmqProperties.getPort();
				String username = RabbitmqProperties.getUsername();
				String password = RabbitmqProperties.getPassword();

				ConnectionFactory factory = new ConnectionFactory();
				factory.setHost(host);
				factory.setPort(port);
				factory.setUsername(username);
				factory.setPassword(password);
				connection = factory.newConnection();
			} catch (IOException | TimeoutException e) {
				throw new RuntimeException(e);
			}
			return connection;
		}
	};

	public static Connection getConnection() {
		return connectionThreadLocal.get();
	}
}
