package com.smxknife.jmx.demo01.instrumentation;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.time.LocalTime;
import java.util.Date;

public class User extends NotificationBroadcasterSupport implements UserMBean {

	private int id;
	private String name;
	private String password;
	private Date birthDate;
	private LocalTime time;
	private Test test;

	private long sequenceNumber = 1;

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public synchronized void setName(String name) {

		Notification n = new AttributeChangeNotification(this,
				sequenceNumber++, System.currentTimeMillis(),
				"name changed", "name", "String", this.name,
				name);
		//发送通知
		this.name = name;
		sendNotification(n);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setBirthDate(Date date) {
		this.birthDate = date;
	}

	@Override
	public Date getBirthDate() {
		return this.birthDate;
	}

	@Override
	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public LocalTime getTime() {
		return this.time;
	}

	@Override
	public void printUserInfo() {
		System.out.printf("User: { id=%s, name=%s }\r\n", this.id, this.name);
	}

	@Override
	public Date currentDate() {
		return new Date();
	}

	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public Test getTest() {
		return this.test;
	}

//	@Override
//	public MBeanNotificationInfo[] getNotificationInfo() {
//		String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
//		String name = AttributeChangeNotification.class.getName();
//		String description = "An attribute of this MBean has changed";
//		MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
//				description);
//		return new MBeanNotificationInfo[] { info };
//	}
}
