package com.smxknife.cache.custom.store.impl;

import com.smxknife.cache.custom.store.DataStore;
import com.smxknife.cache.custom.store.StoreAccessException;
import com.smxknife.cache.custom.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/6/8
 */
public class LRUDataStore<K, V> implements DataStore<K, V> {

	private final int maxSize;
	private final ConcurrentHashMap<K, LRUEntry<K, ValueHolder<?>>> map = new ConcurrentHashMap<>();

	private LRUEntry<K, ValueHolder<?>> first;
	private LRUEntry<K, ValueHolder<?>> last;

	public LRUDataStore(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public ValueHolder<V> get(K key) throws StoreAccessException {
		LRUEntry<K, ValueHolder<?>> entry = getEntry(key);
		if (entry == null) {
			return null;
		}
		moveToFirst(entry);
		return (ValueHolder<V>) entry.getValue();
	}

	@Override
	public void put(K key, V value) throws StoreAccessException {
		LRUEntry<K, ValueHolder<?>> entry = getEntry(key);

		if (entry == null) {
			if (map.size() >= maxSize) {
				map.remove(last.getKey());
				removeLast();
			}
			entry = new LRUEntry<K, ValueHolder<?>>(key, new BasicValueHolder<V>(value));
		} else {
			entry.setValue(new BasicValueHolder<V>(value));
		}
		moveToFirst(entry);
		map.put(key, entry);
	}

	@Override
	public ValueHolder<V> remove(K key) throws StoreAccessException {
		LRUEntry<K, ValueHolder<?>> entry = getEntry(key);
		if (entry == null) {
			return null;
		} else {
			if (entry.getPre() != null) {
				entry.getPre().setNext(entry.getNext());
			}
			if (entry.getNext() != null) {
				entry.getNext().setPre(entry.getPre());
			}
			if (entry == first) {
				first = entry.getNext();
			}
			if (entry == last) {
				last = entry.getPre();
			}
		}
		LRUEntry<K, ValueHolder<?>> oldValue = map.remove(key);
		return (ValueHolder<V>) oldValue.getValue();
	}

	@Override
	public void clear() throws StoreAccessException {
		this.map.clear();
		this.first = this.last = null;
	}

	private void removeLast() {
		if (last != null) {
			last = last.getPre();
			if (last == null) {
				first = null;
			} else {
				last.setNext(null);
			}
		}
	}

	private void moveToFirst(LRUEntry<K, ValueHolder<?>> entry) {
		if (entry == first) {
			return;
		}
		if (entry.getPre() != null) {
			entry.getPre().setNext(entry.getNext());
		}
		if (entry.getNext() != null) {
			entry.getNext().setPre(entry.getPre());
		}
		if (entry == last) {
			last = last.getPre();
		}

		if (first == null || last == null) {
			first = last = entry;
			return;
		}

		entry.setNext(first);
		first.setPre(entry);
		first = entry;
		entry.setPre(null);
	}

	private LRUEntry<K, ValueHolder<?>> getEntry(K key) {
		return map.get(key);
	}
}
