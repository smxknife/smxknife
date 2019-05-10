package com.smxknife.datastructure.queue;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author smxknife
 * 2019-05-10
 */
public class AbsLinkedQueue<T> extends AbsQueue<T> {

	private Node<T> head;
	private Node<T> tail;

	@Override
	public void push(T t) {
		if (Objects.isNull(head)) head = tail = new Node<>(t, null);
		else {
			tail = new Node<>(t, tail);
		}
		this.count++;
	}

	@Override
	public T pop() {
		if (isEmpty()) return null;
		T oldHead = head.item;
		head = head.next;
		if (Objects.nonNull(head)) {
			head.prev = null;
		}
		this.count--;
		return oldHead;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueItr();
	}

	private class Node<T> {
		T item;
		Node<T> next;
		Node<T> prev;

		public Node(T t, Node prev) {
			this.item = t;
			this.prev = prev;
			this.next = null;
			if (Objects.nonNull(prev)) prev.next = this;
		}
	}

	private class QueueItr implements Iterator<T> {

		private Node<T> cursor = head;

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
}
