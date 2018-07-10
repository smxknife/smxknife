package com.smxknife.jmx.demo01.agent;

import com.smxknife.jmx.demo01.instrumentation.HelloImpl;
import com.smxknife.jmx.demo01.instrumentation.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HelloAgent {

	private MBeanServer server;

	public HelloAgent() throws Exception {
		server = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("HelloAgent:type=Hello");

		Test test = new Test();
		test.setName("mytest");
		test.setAge(11);
		HelloImpl hello = new HelloImpl();
		hello.setTest(test);
		hello.setName("hello world");
		server.registerMBean(hello, name);

		Thread.sleep(Long.MAX_VALUE);
	}
}
