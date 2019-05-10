package com.smxknife.datastructure.stack;

/**
 * @author smxknife
 * 2019-05-09
 */
public interface Stack<T> extends Iterable<T> {

	void push(T t);
	T pop();
	boolean isEmpty();
	boolean isFull();
	int size();
}
