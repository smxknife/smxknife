package com.smxknife.java2.thread.thread._02start;

/**
 * @author smxknife
 * 2019/10/11
 */
public class _Run {
	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("MyGroup");
		Thread thread = new Thread(group, () -> System.out.println("xxx"));
		thread.start();
		System.out.println("activeCount: " + group.activeCount());
	}
}
