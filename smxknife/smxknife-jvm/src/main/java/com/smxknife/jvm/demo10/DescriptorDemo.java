package com.smxknife.jvm.demo10;

public class DescriptorDemo implements Runnable{

	public String strField;
	public int intField;

	@Override
	public void run() {

	}

	public int intMethod (int value, double dVal, String strField) {
		return value;
	}

	public double doubleMethod() {
		return 0.0;
	}
}
