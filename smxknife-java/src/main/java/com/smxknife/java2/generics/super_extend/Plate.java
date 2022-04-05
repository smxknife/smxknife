package com.smxknife.java2.generics.super_extend;

/**
 * @author smxknife
 * 2020/11/5
 */
public class Plate<T> {
	private T item;
	public Plate(T t) {
		item = t;
	}

	public void setItem(T t) {
		this.item = t;
	}

	public T getItem() {
		return this.item;
	}
}
