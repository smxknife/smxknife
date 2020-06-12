package com.smxknife.cache.custom;

import com.smxknife.cache.custom.store.DataStore;
import com.smxknife.cache.custom.store.StoreAccessException;
import com.smxknife.cache.custom.store.ValueHolder;

/**
 * @author smxknife
 * 2020/6/8
 */
public class CsCache<K, V> {
	private final DataStore<K, V> store;

	public CsCache(final DataStore<K, V> dataStore) {
		this.store = dataStore;
	}

	public V get(final K key) {
		try {
			ValueHolder<V> valueHolder = store.get(key);
			if (null == valueHolder) {
				return null;
			}
			return valueHolder.value();
		} catch (StoreAccessException e) {
			System.err.println("store access error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void put(final K key, final V value) {
		try {
			store.put(key, value);
		} catch (StoreAccessException e) {
			System.err.println("store access error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public V remove(K key) {
		try {
			ValueHolder<V> valueHolder = store.remove(key);
			return null == valueHolder ? null : valueHolder.value();
		} catch (StoreAccessException e) {
			System.err.println("store access error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void clear() {
		try {
			store.clear();
		} catch (StoreAccessException e) {
			System.err.println("store access error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
