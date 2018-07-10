package com.smxknife.jmx.demo01.instrumentation;

public interface HelloMXBean {

	public String getName();

	public void setName(String name);

	public void setTest(Test test);

	public Test getTest();

}
