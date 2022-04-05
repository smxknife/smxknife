package com.smxknife.java2.collections.deque;

import java.util.ArrayDeque;

/**
 * @author smxknife
 * 2020/7/20
 */
public class ArrayDequeDemo2 {
	public static void main(String[] args) {
		ArrayDeque<String> deque = new ArrayDeque<>();
		deque.addFirst("nihao");
		//deque.addFirst("hello");

//		String pop = deque.pop();
//		pop = deque.pop();
//		pop = deque.pop();
//		System.out.println(pop);

		deque.addLast("d_last");
		deque.addLast("h_last");
	}
}
