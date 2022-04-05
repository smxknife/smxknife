package com.smxknife.java2.thread.thread._02start;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_Jvm {
	public static void main(String[] args) {
		new Thread(() -> {
			while (true) {
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
				Math.random();
			}
		}, "start_jvm_view")
				.start();
	}
}
