package com.smxknife.java2.nio.buffer;

import java.nio.*;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _02_Limit {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};

		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

		System.out.println("byteBuffer.capacity = " + byteBuffer.capacity() + " | byteBuffer.limit = " + byteBuffer.limit());

		byteBuffer.limit(3);

		System.out.println("byteBuffer.capacity = " + byteBuffer.capacity() + " | byteBuffer.limit = " + byteBuffer.limit());

		System.out.println("buffer[0] = " + byteBuffer.get(0));
		System.out.println("buffer[1] = " + byteBuffer.get(1));
		System.out.println("buffer[2] = " + byteBuffer.get(2));
		//System.out.println("buffer[3] = " + byteBuffer.get(3));
		//System.out.println("buffer[4] = " + byteBuffer.get(4));
		//System.out.println("buffer[5] = " + byteBuffer.get(5));

		byteBuffer.put(0, (byte) 0);
		byteBuffer.put(1, (byte) 1);
		byteBuffer.put(2, (byte) 2);
		//byteBuffer.put(3, (byte) 3);
		//byteBuffer.put(4, (byte) 4);
		//byteBuffer.put(5, (byte) 5);
	}
}
