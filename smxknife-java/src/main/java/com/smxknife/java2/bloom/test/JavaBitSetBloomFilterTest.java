package com.smxknife.java2.bloom.test;

import com.smxknife.java2.bloom.BloomHash;
import com.smxknife.java2.bloom.JavaBitSetBloomFilter;

/**
 * @author smxknife
 * 2021/4/13
 */
public class JavaBitSetBloomFilterTest {
	public static void main(String[] args) {
		JavaBitSetBloomFilter filter = new JavaBitSetBloomFilter(new BloomHash[] { new BloomHash.DefaultBloomHash()});


		filter.add(12);
		filter.add(20);

		System.out.println(filter.filter(10));
		System.out.println(filter.filter(11));
		System.out.println(filter.filter(12));
		System.out.println(filter.filter(13));
		System.out.println(filter.filter(20));
	}
}
