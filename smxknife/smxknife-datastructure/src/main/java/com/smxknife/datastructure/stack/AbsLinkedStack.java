package com.smxknife.datastructure.stack;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author smxknife
 * 2019-05-09
 */
abstract class AbsLinkedStack<T> extends AbsStack<T> {

	private Node<T> top;

	private class Node<T> {
		T item;
		Node<T> next;

		public Node(T t, Node<T> next) {
			this.item = t;
			this.next = next;
		}
	}

	@Override
	public void push(T t) {
		top = new Node<>(t, top);
		this.count++;
	}

	@Override
	public T pop() {
		if (this.isEmpty()) return null;
		T popT = top.item;
		top = top.next;
		this.count--;
		return popT;
	}

	@Override
	public Iterator<T> iterator() {
		return new StackItr();
	}

	private class StackItr implements Iterator<T> {

		private Node<T> cursor = top;

		@Override
		public boolean hasNext() {
			return Objects.nonNull(cursor);
		}

		@Override
		public T next() {
			T t = cursor.item;
			cursor = cursor.next;
			return t;
		}
	}

	@Override
	public boolean isFull() {
		throw new UnsupportedOperationException("LinkedStack is not support isFull method");
	}
}
