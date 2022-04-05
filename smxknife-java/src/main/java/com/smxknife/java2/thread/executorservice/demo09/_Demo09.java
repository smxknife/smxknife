package com.smxknife.java2.thread.executorservice.demo09;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2021/5/19
 */
public class _Demo09 {
	public static void main(String[] args) throws InterruptedException {
		/**
		 * ArrayBlockingQueue 只能有届
		 * 此时
		 * - core：2
		 * - max：4
		 * - timeout：
		 * - queue：2
		 * -----------------
		 * - 1。当线程数未达到core时，池中2个线程，一直存在，来线程就会直接运行
		 * - 2。当超过core时，还没有达到max，（这时候会卡，估计在创建线程），最大容忍4个线程同时运行
		 * - 3。当超过max，线程就会进入queue，而queue只有2个空间，所以只能接收两个，等待池中线程执行结束，再执行
		 * - 4。池中最大能容纳的线程数：max+queue。size = 4+2=6，所以，当存在6个时，其他的再进来就跟拒绝策略相关来
		 * - 5。当max生效时，那么timeout肯定也会生效
		 */
		//core2Max4T10ArrayQueue2();

		/**
		 * LinkedBlockingQueue 带容量，这与ArrayQueue一样了
		 */
		//core2Max4T10LinkedQueue2();

		/**
		 * LinkedBlockingQueue不带容量（隐含Integer.MAX_VALUE）
		 * 此时
		 * - max失效，顺带着timeout也失效，只有core
		 * - 超过core后进入queue
		 * - 只要没有达到Integer.MAX_VALUE，永不会拒绝
		 */
		// core2Max4T10LinkedQueue();

		/**
		 * SyncQueue 不带容量，所以最大只能是是max
		 * 超过max就执行拒绝策略
		 */
		core2Max4T10SyncQueue();
	}

	private static void core2Max4T10SyncQueue() throws InterruptedException {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

		Object lock = new Object();

		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				int j = i;
				executor.execute(() -> {
					System.out.println("th_" + j + " | " + executor.getActiveCount() + " | " + executor.getTaskCount() + " | " + executor.getPoolSize() + " ｜ " + executor.getQueue().size());
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		TimeUnit.SECONDS.sleep(20);
		System.out.println(executor.getActiveCount());
	}

	private static void core2Max4T10LinkedQueue() throws InterruptedException {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

		Object lock = new Object();

		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				int j = i;
				executor.execute(() -> {
					System.out.println("th_" + j + " | " + executor.getActiveCount() + " | " + executor.getTaskCount() + " | " + executor.getPoolSize() + " ｜ " + executor.getQueue().size());
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		TimeUnit.SECONDS.sleep(20);
		System.out.println(executor.getActiveCount());
	}

	private static void core2Max4T10LinkedQueue2() throws InterruptedException {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.AbortPolicy());

		Object lock = new Object();

		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				int j = i;
				executor.execute(() -> {
					System.out.println("th_" + j + " | " + executor.getActiveCount() + " | " + executor.getTaskCount() + " | " + executor.getPoolSize() + " ｜ " + executor.getQueue().size());
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		TimeUnit.SECONDS.sleep(20);
		System.out.println(executor.getActiveCount());
	}

	private static void core2Max4T10ArrayQueue2() throws InterruptedException {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue(2), new ThreadPoolExecutor.AbortPolicy());

		Object lock = new Object();

		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				int j = i;
				executor.execute(() -> {
					System.out.println("th_" + j + " | " + executor.getActiveCount() + " | " + executor.getTaskCount() + " | " + executor.getPoolSize() + " ｜ " + executor.getQueue().size());
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		TimeUnit.SECONDS.sleep(20);
		System.out.println(executor.getActiveCount());
	}
}
