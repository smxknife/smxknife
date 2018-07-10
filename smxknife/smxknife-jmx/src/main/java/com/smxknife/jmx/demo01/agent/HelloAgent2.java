package com.smxknife.jmx.demo01.agent;

import com.smxknife.jmx.demo01.instrumentation.HelloImpl2;
import com.smxknife.jmx.demo01.instrumentation.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HelloAgent2 {

	private MBeanServer server;

	public HelloAgent2() throws Exception {
		server = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("HelloAgent2:type=Hello");

		Test test = new Test();
		test.setName("mytest");
		test.setAge(11);
		HelloImpl2 hello = new HelloImpl2();
		hello.setTest(test);
		hello.setName("hello world");
		server.registerMBean(hello, name);

		Thread.sleep(Long.MAX_VALUE);
	}
}
