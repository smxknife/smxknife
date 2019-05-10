package com.smxknife.datastructure.queue;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-10
 */
public class LinkedQueueTest {

	@Test
	public void test() {
		Queue<String> queue = new LinkedQueue<>();

		System.out.println(queue.size());
		System.out.println(queue.isEmpty());

		System.out.println("---------");
		queue.push("aaa");
		queue.push("bbb");
		queue.push("ccc");
		queue.push("aaa");
		queue.push("ddd");
		queue.push("eee");
		queue.push("fff");

		System.out.println(queue.size());
		System.out.println(queue.isEmpty());

		System.out.println("---------------");

		Iterator<String> iterator = queue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------");
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());


	}
}
