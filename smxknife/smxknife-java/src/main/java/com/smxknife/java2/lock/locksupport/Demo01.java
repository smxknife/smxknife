package com.smxknife.java2.lock.locksupport;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/4/23
 */
public class Demo01 {
	public static void main(String[] args) throws InterruptedException {
		Demo01 demo01 = new Demo01();
		Thread.setDefaultUncaughtExceptionHandler((thread, err) -> {
			System.err.println(thread.getName() + ":" + err.getMessage());
		});
		Thread[] threads = IntStream.iterate(0, idx -> idx + 1).limit(5).mapToObj(idx -> {
			return new Thread(() -> {
				boolean exec = true;
				while (exec) {
					demo01.action();
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "th_" + idx);
		}).toArray(Thread[]::new);

		for (Thread thread : threads) {
			thread.start();
		}

		new Timer("timer").scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				int idx = ThreadLocalRandom.current().nextInt(threads.length);
				Thread thread = threads[idx];
				System.out.println("random thread is " + thread.getName());
				Object blocker = LockSupport.getBlocker(thread);
				System.out.println("blocker is " + blocker);
				LockSupport.unpark(thread);
			}
		}, 500, 5000);

//		TimeUnit.SECONDS.sleep(5);
//		Object blocker = LockSupport.getBlocker(threads[0]);
//		System.out.println(blocker);
//		LockSupport.unpark(threads[0]);
	}

	private void action() {
		System.out.println(Thread.currentThread().getName() + " is going to be parked!!");
		LockSupport.park(this);
		System.out.println(Thread.currentThread().getName() + " is acting!");
	}
}
