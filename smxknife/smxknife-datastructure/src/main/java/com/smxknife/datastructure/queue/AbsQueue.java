package com.smxknife.datastructure.queue;

/**
 * @author smxknife
 * 2019-05-10
 */
abstract class AbsQueue<T> implements Queue<T> {

	protected int count = 0;
	protected int capacity = DEFAULT_SIZE;
	protected static final int DEFAULT_SIZE = 5;

	@Override
	public boolean isEmpty() {
		boolean is = this.size() <= 0;
		if (is) this.count = 0;
		return is;
	}

	@Override
	public boolean isFull() {
		return this.size() == this.capacity;
	}

	@Override
	public int size() {
		return this.count;
	}
}
