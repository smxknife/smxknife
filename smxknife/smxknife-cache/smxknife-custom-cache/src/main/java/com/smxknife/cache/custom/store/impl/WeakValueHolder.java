package com.smxknife.cache.custom.store.impl;

import com.smxknife.cache.custom.store.ValueHolder;

import java.lang.ref.WeakReference;

/**
 * @author smxknife
 * 2020/6/8
 */
public class WeakValueHolder<V> implements ValueHolder<V> {

	private WeakReference<V> vRef;

	public WeakValueHolder(V value) {
		if (null == value) {
			return;
		}
		this.vRef = new WeakReference<>(value);
	}

	@Override
	public V value() {
		return this.vRef.get();
	}
}
