package com.smxknife.java2.collections.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author smxknife
 * 2020/7/9
 */
public class LinkedListDemo {
	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(3);
		linkedList.add(1);
		linkedList.add(2);

		System.out.println(linkedList);
		Iterator<Integer> descendingIterator = linkedList.descendingIterator();
		while (descendingIterator.hasNext()) {
			System.out.print(descendingIterator.next());
			System.out.print(", ");
		}
		System.out.println();

		System.out.println("element() : " + linkedList.element());
		System.out.println("offer(5) : " + linkedList.offer(5) + " | " + linkedList);
		System.out.println("offerFirst(10) : " + linkedList.offerFirst(10) + " | " + linkedList);
		System.out.println("offerLast(20) : " + linkedList.offerLast(20) + " | " + linkedList);
		linkedList.push(120);
		System.out.println("push(20) | " + linkedList);
		System.out.println("getFirst() : " + linkedList.getFirst() + " | " + linkedList);
		System.out.println("getLast() : " + linkedList.getLast() + " | " + linkedList);
		System.out.println("peek() : " + linkedList.peek() + " | " + linkedList);
		System.out.println("peekFirst() : " + linkedList.peekFirst() + " | " + linkedList);
		System.out.println("peekLast() : " + linkedList.peekLast() + " | " + linkedList);
		System.out.println("poll() : " + linkedList.poll() + " | " + linkedList);
		System.out.println("pollFirst() : " + linkedList.pollFirst() + " | " + linkedList);
		System.out.println("pollLast() : " + linkedList.pollLast() + " | " + linkedList);
		System.out.println("pop() : " + linkedList.pop() + " | " + linkedList);
		System.out.println("remove() : " + linkedList.remove() + " | " + linkedList);
		System.out.println("removeFirst() : " + linkedList.removeFirst() + " | " + linkedList);
		System.out.println("removeLast() : " + linkedList.removeLast() + " | " + linkedList);

		System.out.println("集合为空时，测试弹出移除");
		// System.out.println("remove : " + linkedList.remove()); // 抛出异常
		// System.out.println("removeFirst : " + linkedList.removeFirst()); // 抛出异常
		// System.out.println("removeLast : " + linkedList.removeLast()); // 抛出异常
		// System.out.println("pop : " + linkedList.pop()); // 抛出异常
		System.out.println("remove不带参数系列和pop都会抛出异常");
		// System.out.println("get : " + linkedList.get(0));
		// System.out.println("getFirst : " + linkedList.getFirst());
		// System.out.println("getLast : " + linkedList.getLast());
		System.out.println("get系列都会抛出异常");

		System.out.println("peek() : " + linkedList.peek());
		System.out.println("peekFirst() : " + linkedList.peekFirst());
		System.out.println("peekLast() : " + linkedList.peekLast());
		System.out.println("peek系列都会返回null");
		System.out.println("poll : " + linkedList.poll());
		System.out.println("pollFirst : " + linkedList.pollFirst());
		System.out.println("pollLast : " + linkedList.pollLast());
		System.out.println("poll系列都会返回null");

	}
}
