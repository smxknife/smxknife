package com.smxknife.java2.unsafe;

import sun.misc.Unsafe;

/**
 * @author smxknife
 * 2019-08-21
 */
public class CASCounter {

	private volatile long counter = 0;
	private Unsafe unsafe;
	private long offset;

	public CASCounter(Unsafe unsafe) {
		this.unsafe = unsafe;
		try {
			offset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("counter"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public long increment() {
		long before = counter;
		System.out.println(Thread.currentThread().getName() + " | enter increment | counter = " + counter);
		while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
			System.out.println(Thread.currentThread().getName() + " | counter = " + counter);
			before = counter;
		}
		return counter;
	}

	public long getCounter() {
		return counter;
	}

}
