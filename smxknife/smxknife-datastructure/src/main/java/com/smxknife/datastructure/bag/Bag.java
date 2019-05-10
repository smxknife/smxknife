package com.smxknife.datastructure.bag;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author smxknife
 * 2019-05-10
 */
public class Bag<T> implements Iterable<T> {

	private Node<T> first;

	public void add(T t) {
		first = new Node<>(t, first);
	}


	@Override
	public Iterator<T> iterator() {
		return new BagItr();
	}

	private class Node<T> {
		T item;
		Node<T> next;

		public Node(T t, Node<T> next) {
			this.item = t;
			this.next = next;
		}
	}

	private class BagItr implements Iterator<T> {

		private Node<T> cursor = first;

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
