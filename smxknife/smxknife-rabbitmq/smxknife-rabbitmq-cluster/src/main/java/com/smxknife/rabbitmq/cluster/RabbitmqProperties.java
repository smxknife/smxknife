package com.smxknife.rabbitmq.cluster;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/5/6
 */
public class RabbitmqProperties {
	private static final String DEFAULT_PATH = "rabbitmq.properties";
	private static Properties properties = new Properties();

	private static final String RABBITMQ_HOST = "rabbitmq.host";
	private static final String RABBITMQ_PORT = "rabbitmq.port";
	private static final String RABBITMQ_USERNAME = "rabbitmq.username";
	private static final String RABBITMQ_PASSWORD = "rabbitmq.password";

	private static String host;
	private static int port;
	private static String username;
	private static String password;

	static void loadProperties(String path, ClassLoader classLoader) throws IOException {
		String propPath = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		ClassLoader loader = Objects.nonNull(classLoader) ? classLoader : RabbitmqProperties.class.getClassLoader();

		InputStream resourceAsStream = loader.getResourceAsStream(propPath);
		properties.load(resourceAsStream);
	}

	static void loadProperties() throws IOException {
		loadProperties(null, null);
	}

	public static String getHost() {
		if (StringUtils.isNotEmpty(host)) {
			return host;
		}
		host = properties.getProperty(RABBITMQ_HOST);
		if (StringUtils.isEmpty(host)) {
			throw new MissingResourceException(String.format( "%s attribute is not exist", RABBITMQ_HOST), "", "");
		}
		return host;
	}

	public static String getUsername() {
		if (StringUtils.isNotEmpty(username)) {
			return username;
		}
		username = properties.getProperty(RABBITMQ_USERNAME);
		return username;
	}

	public static String getPassword() {
		if (StringUtils.isNotEmpty(password)) {
			return password;
		}
		password = properties.getProperty(RABBITMQ_PASSWORD);
		return password;
	}

	public static int getPort() {
		if (port > 0) {
			return port;
		}
		String strPort = properties.getProperty(RABBITMQ_PORT);
		if (StringUtils.isEmpty(strPort)) {
			throw new MissingResourceException(String.format( "%s attribut is not exist", RABBITMQ_PORT), "", "");
		}
		try {
			port = Integer.valueOf(strPort);
			if (port < 1) {
				throw new IllegalArgumentException(String.format("%s must be gather 0", RABBITMQ_PORT));
			}
		} catch (Exception e) {
			throw new RuntimeException(new InvalidPropertiesFormatException(String.format("%s must be number", RABBITMQ_PORT)));
		}
		return port;
	}

}
