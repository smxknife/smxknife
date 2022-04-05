package com.smxknife.java2.bitset;

import java.util.BitSet;

/**
 * @author smxknife
 * 2020/5/6
 */
public class BitSetDemo2 {
	public static void main(String[] args) {
		BitSet bitSet1 = new BitSet(10);
		bitSet1.set(1);
		bitSet1.set(2);
		bitSet1.set(3);

		BitSet bitSet2 = new BitSet(10);
		bitSet2.set(4);
		bitSet2.set(5);

		bitSet1.or(bitSet2);
		System.out.println("len: " + bitSet1.length());
		bitSet1.stream().forEach(System.out::println);

		System.out.println("----------------");
		BitSet bitSet3 = new BitSet(10);
		bitSet3.set(1);
		bitSet3.set(2);
		bitSet1.and(bitSet3);
		System.out.println("len: " + bitSet1.length());
		bitSet1.stream().forEach(System.out::println);
		System.out.println("----------------");

		bitSet1.andNot(bitSet3);
		System.out.println("len: " + bitSet1.length());
		bitSet1.stream().forEach(System.out::println);
		System.out.println("----------------");

		bitSet1.set(1);
		bitSet1.set(2);
		BitSet bitSet4 = new BitSet(10);
		bitSet4.set(2);
		bitSet4.set(3);
		boolean intersects = bitSet1.intersects(bitSet4);
		System.out.println("intersects: " + intersects);
		System.out.println("----------------");

		BitSet bitSet5 = new BitSet(10);
		bitSet5.set(3);
		bitSet5.set(4);
		boolean intersects2 = bitSet1.intersects(bitSet5);
		System.out.println("intersects2: " + intersects2);
		System.out.println("----------------");

		bitSet1.xor(bitSet5);
		System.out.println("len: " + bitSet1.length());
		bitSet1.stream().forEach(System.out::println);
		System.out.println("----------------");

		bitSet1.xor(bitSet4);
		System.out.println("len: " + bitSet1.length());
		bitSet1.stream().forEach(System.out::println);
		System.out.println("----------------");

		int i = bitSet1.nextClearBit(0);
		System.out.println("from 0 nextClear: " + i);
		int i1 = bitSet1.nextSetBit(0);
		System.out.println("from 0 nextSet: " + i1);

		int i2 = bitSet1.previousClearBit(5);
		System.out.println("from 5 previousClear: " + i2);
		int i3 = bitSet1.previousSetBit(5);
		System.out.println("from 5 previousSet: " + i3);

	}
}
