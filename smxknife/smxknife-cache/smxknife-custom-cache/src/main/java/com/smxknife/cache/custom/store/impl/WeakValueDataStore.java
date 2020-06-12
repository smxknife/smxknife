package com.smxknife.cache.custom.store.impl;

import com.smxknife.cache.custom.store.DataStore;
import com.smxknife.cache.custom.store.StoreAccessException;
import com.smxknife.cache.custom.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/6/8
 */
public class WeakValueDataStore<K, V> implements DataStore<K, V> {

	private ConcurrentHashMap<K, ValueHolder<V>> map = new ConcurrentHashMap<>();

	@Override
	public ValueHolder<V> get(K key) throws StoreAccessException {
		return map.get(key);
	}

	@Override
	public void put(K key, V value) throws StoreAccessException {
		WeakValueHolder<V> valueHolder = new WeakValueHolder<>(value);
		map.put(key, valueHolder);
	}

	@Override
	public ValueHolder<V> remove(K key) throws StoreAccessException {
		return map.remove(key);
	}

	@Override
	public void clear() throws StoreAccessException {
		map.clear();
	}
}
