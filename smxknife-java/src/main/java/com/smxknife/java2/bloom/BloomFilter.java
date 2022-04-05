package com.smxknife.java2.bloom;

/**
 * @author smxknife
 * 2021/4/13
 */
public interface BloomFilter {
	/**
	 * add
	 * @param object
	 */
	void add(Object object);

	/**
	 * filter
	 * @param object
	 * @return
	 */
	boolean filter(Object object);
}
