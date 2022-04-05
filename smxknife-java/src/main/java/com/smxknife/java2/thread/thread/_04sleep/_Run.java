package com.smxknife.java2.thread.thread._04sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/10/11
 */
public class _Run {
	public static void main(String[] args) {
		_Run run = new _Run();
		ThreadGroup group = new ThreadGroup("MyGroup");
		SyncObj syncObj = run.new SyncObj();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(group, () -> {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " - activeCount : " + group.activeCount());
					syncObj.sleep();
				}
			}, "th-" + i);
			executorService.submit(thread);
		}
	}

	class SyncObj {

		public synchronized void syncSleep() {
			try {
				System.out.println(Thread.currentThread().getName() + " - 将要进行sleep");
				TimeUnit.SECONDS.sleep(10);
				System.out.println(Thread.currentThread().getName() + " - 睡眠结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void sleep() {
			try {
				System.out.println(Thread.currentThread().getName() + " - 将要进行sleep");
				TimeUnit.SECONDS.sleep(10);
				System.out.println(Thread.currentThread().getName() + " - 睡眠结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
