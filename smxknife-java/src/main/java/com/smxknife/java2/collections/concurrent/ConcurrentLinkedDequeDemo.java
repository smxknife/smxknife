package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author smxknife
 * 2019/9/9
 */
public class ConcurrentLinkedDequeDemo {
	public static void main(String[] args) {
		ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque();
		deque.add(10);
		deque.add(5);
		deque.add(11);
		deque.add(1);
		System.out.println(deque);

		deque.addFirst(1);
		System.out.println(deque);

		deque.addLast(4);
		System.out.println(deque);

	}
}
