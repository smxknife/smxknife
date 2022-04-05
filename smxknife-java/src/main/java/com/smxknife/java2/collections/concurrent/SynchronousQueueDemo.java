package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * @author smxknife
 * 2019/9/9
 */
public class SynchronousQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<Integer> queue = new SynchronousQueue(true);

		boolean offer = queue.offer(1);
		System.out.println(queue + " | " + offer);
//		boolean add = queue.add(1);
//		System.out.println(add);

//		Integer take = queue.take();
//		System.out.println(take + " | after take queue = " + queue);

		new Thread(() -> {
			try {
				queue.put(1);
				System.out.println(Thread.currentThread().getName() + " put success");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "th-put-1").start();

		new Thread(() -> {
			try {
				queue.put(2);
				System.out.println(Thread.currentThread().getName() + " put success");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "th-put-2").start();

		new Thread(() -> {
			try {
				Integer take = queue.take();
				System.out.println(Thread.currentThread().getName() + " take success " + take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "th1-take").start();

		new Thread(() -> {
			try {
				Integer take = queue.take();
				System.out.println(Thread.currentThread().getName() + " take success " + take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "th2-take").start();

	}
}
