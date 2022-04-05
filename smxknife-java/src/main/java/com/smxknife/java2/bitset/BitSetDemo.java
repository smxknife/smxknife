package com.smxknife.java2.bitset;

import java.util.BitSet;

/**
 * @author smxknife
 * 2020/4/29
 */
public class BitSetDemo {
	public static void main(String[] args) {
		BitSet bitSet = new BitSet(10);

		System.out.println(bitSet.cardinality());
		bitSet.stream().forEach(System.out::println);
		System.out.println("------------------------");
		long[] longs = bitSet.toLongArray();
		for (int i = 0; i < longs.length; i++) {
			System.out.println(longs[i]);
		}
		System.out.println("=================");

		bitSet.set(1);
		bitSet.set(2);
		bitSet.set(10);

		System.out.println("set后");
		System.out.println(bitSet.cardinality());
		bitSet.stream().forEach(System.out::println);
		System.out.println("------------------------");
		longs = bitSet.toLongArray();
		for (int i = 0; i < longs.length; i++) {
			System.out.println(longs[i]);
		}
		System.out.println("=================");

		System.out.println(bitSet.get(1));
		bitSet.flip(1);
		System.out.println(bitSet.get(1));
		System.out.println("******************");

		System.out.println(bitSet.length());
		for (int i = 0; i < 12; i++) {
			System.out.println(bitSet.get(i));
		}

		System.out.println(bitSet.cardinality());

		System.out.println(Long.bitCount(15));
	}
}
