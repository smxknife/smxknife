package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _09_Array {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		System.out.println(buffer.hasArray());

		ByteBuffer direct = ByteBuffer.allocateDirect(10);
		System.out.println(direct.hasArray());

		System.out.println(buffer.arrayOffset());
		// System.out.println(direct.arrayOffset()); // 底层实现不是数组所以不支持arrayOffset

		buffer.put((byte) 0);
		System.out.println(buffer.arrayOffset());

	}
}
