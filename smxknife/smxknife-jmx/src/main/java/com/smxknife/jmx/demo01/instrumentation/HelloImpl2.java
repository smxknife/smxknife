package com.smxknife.jmx.demo01.instrumentation;

public class HelloImpl2 implements Hello2 {

	private String name;
	private Test test;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public Test getTest() {
		return this.test;
	}
}
