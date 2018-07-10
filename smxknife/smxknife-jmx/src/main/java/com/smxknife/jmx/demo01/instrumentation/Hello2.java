package com.smxknife.jmx.demo01.instrumentation;

import javax.management.MXBean;

@MXBean
public interface Hello2 {

	public String getName();

	public void setName(String name);

	public void setTest(Test test);

	public Test getTest();

}
