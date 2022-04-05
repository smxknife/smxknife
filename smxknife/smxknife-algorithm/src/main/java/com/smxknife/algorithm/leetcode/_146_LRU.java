package com.smxknife.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2021/5/5
 */
public class _146_LRU {
	public static void main(String[] args) {
		final _146_LRUCache lruCache = new _146_LRUCache(1);
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		System.out.println(lruCache.get(1) + " ");
		lruCache.put(3, 3);
		System.out.println(lruCache.get(2) + " ");
		lruCache.put(4, 4);
		System.out.println(lruCache.get(1) + " ");
		System.out.println(lruCache.get(3) + " ");
		System.out.println(lruCache.get(4) + " ");
	}
}

class _146_LRUCache {

	private int capacity;
	private int count = 0;
	private _146_CacheItem head;
	private _146_CacheItem tail;
	private Map<Integer, _146_CacheItem> valueMap;

	public _146_LRUCache(int capacity) {
		this.capacity = capacity;
		valueMap = new HashMap<Integer, _146_CacheItem>(capacity);
	}

	public int get(int key) {
		// System.out.println("get " + key + "| " + this.valueMap);
		_146_CacheItem item = valueMap.get(key);
		if (item == null) {
			return -1;
		}

		moveToTail(item);
		return item.getValue();

	}

	public void put(int key, int value) {

		_146_CacheItem item = valueMap.get(key);
		if (item != null) {
			item.setValue(value);
			moveToTail(item);
			return;
		}

		if ((count+1) > capacity) {
			popHead();
		}
		count++;
		item = new _146_CacheItem(key, value);
		valueMap.put(key, item);
		if (head == null) {
			head = item;
			tail = item;
		} else {
			tail.setNext(item);
			item.setPre(tail);
			tail = item;
		}
		// System.out.println("put " + key + "| " + this.count + " -- " + this.valueMap);
	}

	private void popHead() {
		count--;
		_146_CacheItem newHeadItem = this.head.getNext();
		final Integer key = this.head.getKey();
		this.valueMap.remove(key);
		if (newHeadItem == null) {
			head = null;
			tail = null;
			return;
		}
		head.setNext(null);
		newHeadItem.setPre(null);
		head = newHeadItem;
	}

	private void moveToTail(_146_CacheItem item) {
		if (tail == item) {
			return;
		}
		_146_CacheItem nextItem = item.getNext();
		if (head == item) {
			head = nextItem;
			nextItem.setPre(null);
			tail.setNext(item);
			item.setPre(tail);
			tail = item;
			item.setNext(null);
			return;
		}

		_146_CacheItem preItem = item.getPre();
		preItem.setNext(nextItem);
		nextItem.setPre(preItem);
		tail.setNext(item);
		item.setNext(null);
		item.setPre(tail);
		tail = item;
	}
}

class _146_CacheItem {
	private Integer key;
	private Integer value;
	private _146_CacheItem pre;
	private _146_CacheItem next;

	public _146_CacheItem(Integer key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return this.key;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public _146_CacheItem getPre() {
		return this.pre;
	}

	public _146_CacheItem getNext() {
		return this.next;
	}

	public void setPre(_146_CacheItem pre) {
		this.pre = pre;
	}

	public void setNext(_146_CacheItem next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Item{" +
				"value=" + value +
				'}';
	}
}