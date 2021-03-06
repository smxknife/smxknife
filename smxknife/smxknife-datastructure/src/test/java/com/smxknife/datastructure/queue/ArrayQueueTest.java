package com.smxknife.datastructure.queue;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-10
 */
public class ArrayQueueTest {

	@Test
	public void test() {
		Queue<String> queue = new ArrayQueue<>();

		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
		System.out.println(queue.size());

		queue.enqueue("aaa");
		queue.enqueue("bbb");
		queue.enqueue("ccc");
		queue.enqueue("ddd");

		System.out.println(queue.size());
		System.out.println("-----------------");
		Iterator<String> iterator = queue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("+++++++++++++++++");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());

		for (int i = 0; i < 50; i++) {
			queue.enqueue("llll" + i);
		}

		System.out.println("-----------------");
		iterator = queue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
