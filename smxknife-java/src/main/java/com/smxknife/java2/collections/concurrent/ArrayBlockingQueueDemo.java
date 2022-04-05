package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author smxknife
 * 2019/9/9
 */
public class ArrayBlockingQueueDemo {
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(5);
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
		System.out.println("------- initial -------");

		queue.add(10);
		queue.add(11);
		queue.add(12);
		queue.add(13);
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());

		queue.add(14);
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());

		System.out.println("----- 队列已经满了 ------");
		System.out.println("add：在队列已经满了的情况，抛出异常\n" +
				"offer：在队列已经满了的情况，不抛出异常，返回false\n" +
				"put：在队列已经满了的情况，阻塞");

		System.out.println("实验：");

		// System.out.println("add 20 | " + queue.add(20));
		System.out.println("offer 30 | " + queue.offer(30));
		try {
			queue.put(40);
			System.out.println("put 40 | true");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
