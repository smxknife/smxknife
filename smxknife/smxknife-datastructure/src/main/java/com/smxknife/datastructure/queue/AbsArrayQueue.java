package com.smxknife.datastructure.queue;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-10
 */
abstract class AbsArrayQueue<T> extends AbsQueue<T> {

	private T[] elements;

	public AbsArrayQueue() {
		this(DEFAULT_SIZE);
	}

	public AbsArrayQueue(int size) {
		this.elements = (T[]) new Object[size];
		this.capacity = size;
	}

	@Override
	public void push(T t) {
		expand(1);
		this.elements[this.count++] = t;
	}

	protected boolean expandable() {
		return true;
	}

	protected void expand(int size) {
		if (!this.needExpand(size)) return;
		if (!this.expandable()) throw new UnsupportedOperationException("Stack is full and disable expand capacity!!!");
		this.capacity = this.capacity << 1; // 扩容为原来的2倍
		T[] newContainer = (T[])new Object[this.capacity];
		System.arraycopy(this.elements, 0, newContainer, 0, this.count);
		this.elements = newContainer;
	}

	/**
	 * 如果新加入的元素数量和现有元素数量大于容量需要扩容
	 * @param size
	 * @return
	 */
	protected boolean needExpand(int size) {
		return (this.count + this.size()) > this.capacity;
	}

	@Override
	public T pop() {
		if (this.isEmpty()) return null;
		T t = elements[0];
		this.elements[0] = null;
		this.count--;
		System.arraycopy(this.elements, 1, this.elements, 0, this.count);
		return t;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueItr();
	}

	private class QueueItr implements Iterator<T> {

		private int idx = 0;

		@Override
		public boolean hasNext() {
			return idx < count;
		}

		@Override
		public T next() {
			T t = elements[idx++];
			return t;
		}
	}
}
