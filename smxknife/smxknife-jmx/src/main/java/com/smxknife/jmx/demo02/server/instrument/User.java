package com.smxknife.jmx.demo02.server.instrument;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.time.LocalDateTime;

public class User extends NotificationBroadcasterSupport implements UserMBean {

	private int id;
	private String name;
	private String password;

	private long sequenceNumber = 1;

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
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
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void printUserInfo() {
		System.out.printf("User: { id=%s, name=%s }\r\n", this.id, this.name);
	}

	@Override
	public LocalDateTime currentDate() {
		return LocalDateTime.now();
	}
}
