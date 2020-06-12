package com.smxknife.rxjava2.demo06;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Log {
	public static void log(String log) {
		System.out.println(Thread.currentThread().getName() + " : " + log);
	}
}
