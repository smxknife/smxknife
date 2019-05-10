package com.smxknife.datastructure.stack;

/**
 * @author smxknife
 * 2019-05-09
 */
abstract class AbsStack<T> implements Stack<T> {

	/**
	 * default capacity
	 */
	static final int DEFAULT_SIZE = 5;
	/**
	 * element count
	 */
	int count = 0;
	int capacity = 0;


	@Override
	public boolean isEmpty() {
		boolean is = this.count <= 0;
		if (is) this.count = 0;
		return is;
	}

	@Override
	public boolean isFull() {
		return this.capacity == this.count;
	}

	@Override
	public int size() {
		return this.count;
	}
}
