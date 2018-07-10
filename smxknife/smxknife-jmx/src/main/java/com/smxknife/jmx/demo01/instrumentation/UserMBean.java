package com.smxknife.jmx.demo01.instrumentation;

import java.time.LocalTime;
import java.util.Date;

public interface UserMBean {

	public void setId(Integer id);
	public Integer getId();
	public void setName(String name);
	public String getName();
	public void setBirthDate(Date date);
	public Date getBirthDate();
	public void setTime(LocalTime time);
	public LocalTime getTime();
	public void printUserInfo();
	public Date currentDate();
	public void setTest(Test test);
	public Test getTest();
}
