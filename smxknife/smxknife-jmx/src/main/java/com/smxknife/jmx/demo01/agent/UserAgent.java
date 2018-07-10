package com.smxknife.jmx.demo01.agent;

import com.smxknife.jmx.demo01.instrumentation.Test;
import com.smxknife.jmx.demo01.instrumentation.User;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.time.LocalTime;
import java.util.Date;

public class UserAgent implements NotificationListener {

	private MBeanServer mBeanServer;
	private MBeanServer mBeanServer1;

	public UserAgent() {
		mBeanServer = ManagementFactory.getPlatformMBeanServer();
		mBeanServer1 = MBeanServerFactory.createMBeanServer("Hello");

		try {
			ObjectName name = new ObjectName("UserAgent:type=User1");

			User user = new User();
			user.setName("test");
			user.setId(1);
			user.setBirthDate(new Date());
			user.setTime(LocalTime.now());
			Test test = new Test();
			test.setName("mytest");
			test.setAge(11);
			user.setTest(test);
			mBeanServer.registerMBean(user, name);

			mBeanServer1.registerMBean(user, new ObjectName("Hello:name=user"));

			Thread.sleep(Long.MAX_VALUE);

		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {

	}
}
