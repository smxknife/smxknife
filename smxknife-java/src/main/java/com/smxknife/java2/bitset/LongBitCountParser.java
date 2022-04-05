package com.smxknife.java2.bitset;

/**
 * @author smxknife
 * 2020/4/29
 */
public class LongBitCountParser {
	public static void main(String[] args) {
		long i = 219;
		System.out.println(Integer.toBinaryString(219));
		System.out.println(String.format("原始值：%d, bitCount：%d", i, Long.bitCount(i)));
		System.out.println("第一步: >>>> i = i - ((i >>> 1) & 0x5555555555555555L)");
		System.out.println(String.format("              Note: 0x5555555555555555L = %s", Long.toBinaryString(0x5555555555555555L)));
		System.out.println(String.format("  i = %s [%d]", Long.toBinaryString(i), i));
		long step = i >>> 1;
		System.out.println(String.format("  i >>> 1 = %s [%d]", Long.toBinaryString(step), step));
		step = ((i >>> 1) & 0x5555555555555555L);
		System.out.println(String.format("  (i >>> 1) & 0x5555555555555555L = %s [%d]", Long.toBinaryString(step), step));
		i = step = i - ((i >>> 1) & 0x5555555555555555L);
		System.out.println(String.format("  i = i - ((i >>> 1) & 0x5555555555555555L) = %s [%d]", Long.toBinaryString(step), step));

		System.out.println("第二步: >>>> i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L)");
		System.out.println(String.format("              Note: 0x3333333333333333L = %s", Long.toBinaryString(0x3333333333333333L)));
		step = i & 0x3333333333333333L;
		System.out.println(String.format("  i & 0x3333333333333333L = %s [%d]", Long.toBinaryString(step), step));
		step = i >>> 2;
		System.out.println(String.format("  i >>> 2 = %s [%d]", Long.toBinaryString(step), step));
		step = (i >>> 2) & 0x3333333333333333L;
		System.out.println(String.format("  (i >>> 2) & 0x3333333333333333L = %s [%d]", Long.toBinaryString(step), step));
		i = step = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		System.out.println(String.format("  i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L) = %s [%d]", Long.toBinaryString(step), step));

		System.out.println("第三步: >>>> i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL");
		System.out.println(String.format("              Note: 0x0f0f0f0f0f0f0f0fL = %s", Long.toBinaryString(0x0f0f0f0f0f0f0f0fL)));

	}

	public static int bitCount(long i) {
		// HD, Figure 5-14
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int)i & 0x7f;
	}
}
