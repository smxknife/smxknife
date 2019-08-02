package com.smxknife.patterns.singleton.demo01;

/**
 * @author smxknife
 * 2019-06-18
 */
public class LazyInitializationDoubleCheck {

	private LazyInitializationDoubleCheck() {}

	private static volatile LazyInitializationDoubleCheck instance = null;

	public static LazyInitializationDoubleCheck getInstance() {
		if (instance == null) {

			synchronized (LazyInitializationDoubleCheck.class) {
				if (instance == null) {
					instance = new LazyInitializationDoubleCheck();
					System.out.println("before current thread = " + Thread.currentThread().getName() + ", instance = " + instance);
				}
			}

			try {
				// 暂停一会，让所有的线程都进入该方法，看最后生成的对象是不是一个
				Thread.sleep(1000);
				System.out.println("after current thread = " + Thread.currentThread().getName() + ", instance = " + instance);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}
