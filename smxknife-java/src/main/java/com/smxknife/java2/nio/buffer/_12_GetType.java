package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _12_GetType {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 0);
		buffer.put((byte) 1);
		buffer.put((byte) 2);
		buffer.put((byte) 3);

		//System.out.println(buffer.get());
		//System.out.println(buffer.get());
		//System.out.println(buffer.get());
		//System.out.println(buffer.get());

		System.out.println("----------------------");

		buffer.flip();

		int anInt = buffer.getInt();
		System.out.println(anInt);



	}
}
