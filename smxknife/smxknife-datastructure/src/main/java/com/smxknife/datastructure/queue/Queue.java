package com.smxknife.datastructure.queue;

/**
 * @author smxknife
 * 2019-05-10
 */
public interface Queue<T> extends Iterable<T> {

	void push(T t);
	T pop();
	boolean isEmpty();
	boolean isFull();
	int size();

}
