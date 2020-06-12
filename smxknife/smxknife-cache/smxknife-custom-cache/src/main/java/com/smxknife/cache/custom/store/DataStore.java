package com.smxknife.cache.custom.store;

/**
 * @author smxknife
 * 2020/6/8
 */
public interface DataStore<K, V> {
	ValueHolder<V> get(K key) throws StoreAccessException;

	void put(K key, V value) throws StoreAccessException;

	ValueHolder<V> remove(K key) throws StoreAccessException;

	void clear() throws StoreAccessException;
}
