package com.smxknife.java2.collections.map;

import java.util.Properties;

/**
 * @author smxknife
 * 2020/7/8
 */
public class PropertiesDemo {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("1", "1");
		properties.setProperty("2", "2");
		properties.setProperty("5", "5");
		properties.setProperty("3", "3");
		System.out.println(properties);
	}
}
