package com.smxknife.java2.nio.buffer;

import java.nio.*;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _01_Capacity {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {1, 2, 3};
		char[] chars = new char[] {'a', 'b', 'c', 'd'};
		int[] ints = new int[] {1, 2, 3, 4, 5};
		float[] floats = new float[] {1, 2, 3, 4, 5, 6};
		short[] shorts = new short[] {1, 2, 3, 4, 5 , 6, 7};
		long[] longs = new long[] {1, 2, 3, 4, 5, 6, 7, 8};
		double[] doubles = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9};

		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		CharBuffer charBuffer = CharBuffer.wrap(chars);
		IntBuffer intBuffer = IntBuffer.wrap(ints);
		FloatBuffer floatBuffer = FloatBuffer.wrap(floats);
		ShortBuffer shortBuffer = ShortBuffer.wrap(shorts);
		LongBuffer longBuffer = LongBuffer.wrap(longs);
		DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubles);

		System.out.println("byteBuffer = " + byteBuffer.getClass().getName());
		System.out.println("charBuffer = " + charBuffer.getClass().getName());
		System.out.println("intBuffer = " + intBuffer.getClass().getName());
		System.out.println("floatBuffer = " + floatBuffer.getClass().getName());
		System.out.println("shortBuffer = " + shortBuffer.getClass().getName());
		System.out.println("longBuffer = " + longBuffer.getClass().getName());
		System.out.println("doubleBuffer = " + doubleBuffer.getClass().getName());

		System.out.println();

		System.out.println("byteBuffer.capacity = " + byteBuffer.capacity());
		System.out.println("charBuffer.capacity = " + charBuffer.capacity());
		System.out.println("intBuffer.capacity = " + intBuffer.capacity());
		System.out.println("floatBuffer.capacity = " + floatBuffer.capacity());
		System.out.println("shortBuffer.capacity = " + shortBuffer.capacity());
		System.out.println("longBuffer.capacity = " + longBuffer.capacity());
		System.out.println("doubleBuffer.capacity = " + doubleBuffer.capacity());
	}
}
