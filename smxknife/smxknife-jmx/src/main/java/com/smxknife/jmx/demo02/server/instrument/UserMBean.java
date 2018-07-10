package com.smxknife.jmx.demo02.server.instrument;

import java.time.LocalDateTime;

public interface UserMBean {
	public void setId(int id);
	public int getId();
	public void setName(String name);
	public String getName();
	public void setPassword(String password);
	public String getPassword();
	public void printUserInfo();
	public LocalDateTime currentDate();
}
