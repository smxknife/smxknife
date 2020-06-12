package com.smxknife.cache.custom.store.impl;

import com.smxknife.cache.custom.store.ValueHolder;

/**
 * @author smxknife
 * 2020/6/8
 */
public class BasicValueHolder<V> implements ValueHolder<V> {

	private final V value;

	public BasicValueHolder(V value) {
		this.value = value;
	}

	@Override
	public V value() {
		return this.value;
	}
}
