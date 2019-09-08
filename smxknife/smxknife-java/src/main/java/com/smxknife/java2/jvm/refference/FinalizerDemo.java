package com.smxknife.java2.jvm.refference;

/**
 * @author smxknife
 * 2019/9/7
 */
public class FinalizerDemo {

	@Override
	protected void finalize() throws Throwable {
		System.out.println();
	}
}
