package com.smxknife.patterns.singleton.demo01;

/**
 * 从输出结果来看，每个线程进入空判断之后，有可能创建，也有可能不创建，但是最后的结果是最后一个创建的
 * 由此可见，使用volatile仅仅只能保证可见性
 * @author smxknife
 * 2019-06-18
 */
public class LazyInitializationDemo {
	public static void main(String[] args) {
//		runWithVolatile();
//		runWithNoVolatile();
//		runWithSynchronized();
		runWithDoubleCheck();
	}

	private static void runWithDoubleCheck() {
		new Thread(() -> LazyInitializationDoubleCheck.getInstance(), "thread_1").start();
		new Thread(() -> LazyInitializationDoubleCheck.getInstance(), "thread_2").start();
		new Thread(() -> LazyInitializationDoubleCheck.getInstance(), "thread_3").start();
		new Thread(() -> LazyInitializationDoubleCheck.getInstance(), "thread_4").start();
		new Thread(() -> LazyInitializationDoubleCheck.getInstance(), "thread_5").start();
	}

	private static void runWithSynchronized() {
		new Thread(() -> LazyInitializationWithSynchronized.getInstance(), "thread_1").start();
		new Thread(() -> LazyInitializationWithSynchronized.getInstance(), "thread_2").start();
		new Thread(() -> LazyInitializationWithSynchronized.getInstance(), "thread_3").start();
		new Thread(() -> LazyInitializationWithSynchronized.getInstance(), "thread_4").start();
		new Thread(() -> LazyInitializationWithSynchronized.getInstance(), "thread_5").start();
	}

	private static void runWithNoVolatile() {
		new Thread(() -> LazyInitializationWithNoVolatile.getInstance(), "thread_1").start();
		new Thread(() -> LazyInitializationWithNoVolatile.getInstance(), "thread_2").start();
		new Thread(() -> LazyInitializationWithNoVolatile.getInstance(), "thread_3").start();
		new Thread(() -> LazyInitializationWithNoVolatile.getInstance(), "thread_4").start();
		new Thread(() -> LazyInitializationWithNoVolatile.getInstance(), "thread_5").start();
	}

	private static void runWithVolatile() {
		new Thread(() -> LazyInitialization.getInstance(), "thread_1").start();
		new Thread(() -> LazyInitialization.getInstance(), "thread_2").start();
		new Thread(() -> LazyInitialization.getInstance(), "thread_3").start();
		new Thread(() -> LazyInitialization.getInstance(), "thread_4").start();
		new Thread(() -> LazyInitialization.getInstance(), "thread_5").start();
	}


}
