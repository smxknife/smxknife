package com.smxknife.java2.collections.concurrent;

/**
 * @author smxknife
 * 2020/7/24
 */
public class CopyOnWriteArrayList_GetArray {
	private transient volatile Object[] array;

	final Object[] getArray() {
		return array;
	}

	public Object get() {
		return getArray()[0];
	}
}
