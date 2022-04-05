package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _16_Slice {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 10);
		buffer.put((byte) 11);
		buffer.put((byte) 12);
		buffer.put((byte) 13);
		buffer.put((byte) 14);
		buffer.put((byte) 15);
		buffer.put((byte) 16);
		buffer.put((byte) 17);
		buffer.put((byte) 18);
		buffer.put((byte) 19);

		buffer.rewind();
		// 上面相当于完成一次初始化

		System.out.println("init finished, position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());

		buffer.limit(8);
		buffer.position(3);
		System.out.println("after set, position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());

		ByteBuffer slice = buffer.slice();
		System.out.println("slice finished, position = " + slice.position() + " | limit = " + slice.limit() + " | capacity = " + slice.capacity());

		System.out.println("-----------------");
		buffer.position(5);
		System.out.println("2. after set, position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());
		System.out.println("2. slice after buffer set, position = " + slice.position() + " | limit = " + slice.limit() + " | capacity = " + slice.capacity());

		slice.position(3);
		System.out.println("3. after set, position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());
		System.out.println("3. slice after buffer set, position = " + slice.position() + " | limit = " + slice.limit() + " | capacity = " + slice.capacity());

		System.out.println("-----------------");
		buffer.rewind();
		slice.rewind();
		System.out.println("buffer 3 = " + buffer.get(3));
		System.out.println("slice 0 = " + slice.get(0));

		buffer.put(3, (byte) 100);
		System.out.println("after buffer set buffer 3 = " + buffer.get(3));
		System.out.println("after buffer set slice 0 = " + slice.get(0));

		slice.put(0, (byte) 1);
		System.out.println("after slice set buffer 3 = " + buffer.get(3));
		System.out.println("after slice set slice 0 = " + slice.get(0));

		System.out.println("------------------");
		System.out.println("buffer arrayOffset = " + buffer.arrayOffset());
		System.out.println("slice arrayOffset = " + slice.arrayOffset());


	}
}
