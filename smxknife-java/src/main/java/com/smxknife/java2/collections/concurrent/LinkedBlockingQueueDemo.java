package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author smxknife
 * 2019/9/9
 */
public class LinkedBlockingQueueDemo {
	public static void main(String[] args) {
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();

		System.out.println(queue.remainingCapacity());
		System.out.println(Integer.toBinaryString(queue.remainingCapacity()));
	}
}
