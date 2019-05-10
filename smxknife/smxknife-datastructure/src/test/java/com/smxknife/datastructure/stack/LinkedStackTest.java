package com.smxknife.datastructure.stack;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-10
 */
public class LinkedStackTest {

	@Test
	public void test() {
		Stack<String> stringStack = new LinkedStack<>();
		stringStack.push("ccc");
		stringStack.push("ddd");
		stringStack.push("eee");
		stringStack.push("fff");

		System.out.println(stringStack.pop());
		Iterator<String> iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------------");
		System.out.println(stringStack.pop());
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------------");
		System.out.println(stringStack.pop());
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------------");
		System.out.println(stringStack.pop());
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------------");
		System.out.println(stringStack.pop());
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("---------------------");
		System.out.println(stringStack.pop());
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		for (int i = 0; i < 100; i++) {
			stringStack.push("while_" + i);
		}
		System.out.println("---------------------");
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}
}
