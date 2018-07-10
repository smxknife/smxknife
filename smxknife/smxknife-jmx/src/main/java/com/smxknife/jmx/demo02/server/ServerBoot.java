package com.smxknife.jmx.demo02.server;

import com.smxknife.jmx.demo01.agent.UserAgent;

public class ServerBoot {
	public static void main(String[] args) {
		System.setProperty("com.sun.management.jmxremote", "true");
//		System.setProperty("java.rmi.server.hostname", "");
		System.setProperty("com.sun.management.jmxremote.ssl", "false");
		System.setProperty("com.sun.management.jmxremote.authenticate", "false");
		UserAgent userAgent = new UserAgent();
	}
}
