package com.smxknife.jmx.demo01.agent;

import com.smxknife.jmx.demo01.instrumentation.QueueSampler;

import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueSampleAgent implements NotificationListener {

	private MBeanServer mBeanServer;
	public QueueSampleAgent() {
		this.mBeanServer = ManagementFactory.getPlatformMBeanServer();

		try {
			ObjectName mxbeanName = new ObjectName(this.getClass().getPackage().getName()+ ":type=QueueSampler");

			Queue<String> queue = new ArrayBlockingQueue<String>(10);
			queue.add("Request-1");
			queue.add("Request-2");
			queue.add("Request-3");
			QueueSampler mxbean = new QueueSampler(queue);

			mBeanServer.registerMBean(mxbean, mxbeanName);

			System.out.println("Waiting...");
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {

		}
	}
	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println(notification.getMessage());
	}
}
