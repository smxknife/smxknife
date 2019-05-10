package com.smxknife.datastructure.stack;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-09
 */
@Slf4j
abstract class AbsArrayStack<T> extends AbsStack<T> {

	/**
	 * 实际存储
	 */
	private T[] elements;

	protected AbsArrayStack() {
		this(DEFAULT_SIZE);
	}

	protected AbsArrayStack(int size) {
		Preconditions.checkArgument(size > 0);
		elements = (T[]) new Object[DEFAULT_SIZE];
		this.capacity = size;
	}

	@Override
	public T pop() {
		if (isEmpty()) return null;
		T  t = elements[--this.count]; // TODO 临界值，需要测试
		elements[this.count] = null; // 清除引用，方便GC回收
		this.compress();
		return t;
	}

	@Override
	public void push(T t) {
		expand(1);
		elements[count++] = t; // 将新加入的元素放入最后，并且数量加一
	}

	/**
	 * 是否支持扩容
	 * @return
	 */
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

	private void compress() {
		// 4是一个估计值，元素数小于容量四分之一，压缩为容量的一半
		if ((this.count <= this.capacity / 4) && (this.capacity / 2 > DEFAULT_SIZE)) {
			this.capacity = this.capacity >> 1;
			this.elements = Arrays.copyOf(this.elements, this.capacity);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new StackItr();
	}

	private class StackItr implements Iterator<T> {

		private int idx = size();

		@Override
		public boolean hasNext() {
			return idx > 0;
		}

		@Override
		public T next() {
			T t = elements[--idx];
			return t;
		}
	}
}
