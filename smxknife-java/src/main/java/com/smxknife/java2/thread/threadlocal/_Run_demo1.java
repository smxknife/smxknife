package com.smxknife.java2.thread.threadlocal;

/**
 * @author smxknife
 * 2021/2/3
 */
public class _Run_demo1 {
	public static void main(String[] args) {
		ThreadLocal<Integer> intTl1 = new ThreadLocal<>();
		ThreadLocal<Integer> intTl2 = new ThreadLocal<Integer>() {
			@Override
			protected Integer initialValue() {
				return -1;
			}
		};

		new Thread(() -> {
			Integer i1 = intTl1.get();
			Integer i2 = intTl2.get();
			System.out.println(i1 + " " + i2);

			intTl1.set(2);
			intTl2.set(2);
			i1 = intTl1.get();
			i2 = intTl2.get();
			System.out.println(i1 + " " + i2);
		}, "t1").start();
	}
}
