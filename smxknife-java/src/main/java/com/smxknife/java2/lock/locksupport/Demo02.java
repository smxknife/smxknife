package com.smxknife.java2.lock.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author smxknife
 * 2020/4/23
 */
public class Demo02 {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程即将阻塞");
			LockSupport.park();
			System.out.println("thread action");
		});
		thread.start();
		for (int i = 0; i < 1; i++) {
			System.out.println("线程唤醒" + i + "次");
			LockSupport.unpark(thread);
		}
	}
}
