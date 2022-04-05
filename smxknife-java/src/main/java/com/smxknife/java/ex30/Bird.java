package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2019/9/8
 */
public enum Bird {
	INSTANCE;

	public void print() {
		System.out.println(Thread.currentThread().getName() + " | " + this);
	}
}
