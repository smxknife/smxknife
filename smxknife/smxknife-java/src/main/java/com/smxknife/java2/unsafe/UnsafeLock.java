package com.smxknife.java2.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author smxknife
 * 2019-08-21
 */
public class UnsafeLock {

	private volatile int status;

	private static final long statusOffset;
	private static final Unsafe UNSAFE;
	private WaitingQueue queue = new WaitingQueue();

	static {
		try {
			UNSAFE = UnsafeWrapper.unsafe();
			Field statusField = UnsafeLock.class.getDeclaredField("status");
			statusField.setAccessible(true);
			statusOffset = UNSAFE.objectFieldOffset(statusField);
		} catch (Exception ex) { throw new Error(ex); }
	}

	public boolean lock() {
		// 竞争成功，继续执行
		if (compareAndSetStatus(0, 1)) {
			return true;
		}
		// 竞争失败，进入等待序列
		push(Thread.currentThread());
		while (!compareAndSetStatus(0, 1)) {
			// 线程挂起
			UNSAFE.park(false, 0);
		}
		return true;
	}

	public boolean unlock() {
		status = 0;
		Thread thread = pop();
		if (Objects.nonNull(thread)) {
			// 线程恢复
			UNSAFE.unpark(thread);
		}
		return true;
	}

	private boolean compareAndSetStatus(int expectVal, int newVal) {
		long offset = UNSAFE.getIntVolatile(this, statusOffset);
		return UNSAFE.compareAndSwapInt(this, statusOffset, expectVal, newVal);
	}

	private void push(Thread thread) {
		queue.push(thread);
	}

	private Thread pop() {
		return queue.pop();
	}

	private static class WaitingQueue {
		private volatile int status;
		private static long statueOffset;

		private LinkedList<Thread> queue = new LinkedList<>();

		static {
			try {
				statueOffset = UNSAFE.objectFieldOffset(WaitingQueue.class.getDeclaredField("status"));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}

		public void push(Thread thread) {
			while (!compareAndSetStatus(0, 1)) {}

			queue.offerLast(thread);
			compareAndSetStatus(1, 0);
		}

		public Thread pop() {
			while (!compareAndSetStatus(0, 1)) { }
			Thread thread = null;
			if (!queue.isEmpty()) {
				thread = queue.pop();
			}
			compareAndSetStatus(1, 0);
			return thread;
		}

		public int size() {
			return queue.size();
		}

		private boolean compareAndSetStatus(int expectVal, int newVal) {
			return UNSAFE.compareAndSwapInt(this, statueOffset, expectVal, newVal);
		}
	}


}
