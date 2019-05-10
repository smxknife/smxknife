package com.smxknife.datastructure.queue;

/**
 * @author smxknife
 * 2019-05-10
 */
public interface Queue<T> extends Iterable<T> {

	void enqueue(T t);
	T dequeue();
	boolean isEmpty();
	boolean isFull();
	int size();

}
