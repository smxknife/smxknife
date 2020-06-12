package com.smxknife.patterns.singleton.demo03;

/**
 * @author smxknife
 * 2019/12/26
 */
public enum EnumRegisterSingleton {
	INSTANCE;

	private EnumRegisterSingleton() {}

	private Object data;

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static EnumRegisterSingleton getInstance() {
		return INSTANCE;
	}
}
