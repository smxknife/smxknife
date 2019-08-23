package com.smxknife.java2.unsafe;

import sun.misc.Unsafe;

/**
 * @author smxknife
 * 2019-08-21
 */
public class HugeArray {
	private final static int BYTE = 1;

	private long size;
	private long address;
	private Unsafe unsafe;

	public HugeArray(long size, Unsafe unsafe) throws FinalException{
		this.unsafe = unsafe;
		this.size = size;
		this.address = unsafe.allocateMemory(size * BYTE);
	}

	public void set(long i, byte byteValue) {
		unsafe.putByte(address + i * BYTE, byteValue);
	}

	public int get(long i) {
		return unsafe.getByte(address + i * BYTE);
	}

	public long size() {
		return size;
	}

	public void release() {
		this.unsafe.freeMemory(address);
	}
}
