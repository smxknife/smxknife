package com.smxknife.cache.custom.store.impl;

import com.smxknife.cache.custom.store.ValueHolder;

import java.util.Map;

/**
 * @author smxknife
 * 2020/6/8
 */
public class LRUEntry<K, V extends ValueHolder<?>> implements Map.Entry<K, ValueHolder<?>> {

	private final K key;
	private ValueHolder<?> value;

	private LRUEntry<K, ValueHolder<?>> pre;
	private LRUEntry<K, ValueHolder<?>> next;

	public LRUEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public LRUEntry<K, ValueHolder<?>> getPre() {
		return pre;
	}

	public void setPre(LRUEntry<K, ValueHolder<?>> pre) {
		this.pre = pre;
	}

	public LRUEntry<K, ValueHolder<?>> getNext() {
		return next;
	}

	public void setNext(LRUEntry<K, ValueHolder<?>> next) {
		this.next = next;
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public ValueHolder<?> getValue() {
		return this.value;
	}

	@Override
	public ValueHolder<?> setValue(ValueHolder<?> value) {
		ValueHolder<?> old = this.value;
		this.value = value;
		return old;
	}
}
