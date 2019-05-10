package com.smxknife.datastructure.stack;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-09
 */
public class ArrayStackTest {

	@Test
	public void test() {
		Stack<String> stringStack = new ArrayStack<>();
		System.out.println(stringStack);

		stringStack.push("aaa");
		stringStack.push("bbb");
		stringStack.push("ccc");

		for (String s : stringStack) {
			System.out.println(s);
		}

		System.out.println("-------");

		System.out.println(stringStack.pop());
		System.out.println("||||");
		Iterator<String> iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		stringStack.pop();
		stringStack.pop();
		stringStack.pop();
		stringStack.pop();
		stringStack.pop();
		stringStack.pop();

		for (int i = 0; i < 50; i++) {
			System.out.println("stack is empty: " + stringStack.isEmpty());
			System.out.println("stack is full: " + stringStack.isFull());
			stringStack.push("ii" + i);
		}

		System.out.println("+++++");
		iterator = stringStack.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println(stringStack.size());
	}
}
