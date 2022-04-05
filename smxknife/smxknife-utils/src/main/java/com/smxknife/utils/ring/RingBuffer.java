package com.smxknife.utils.ring;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2021/6/4
 */
public class RingBuffer {

	private AtomicInteger cursor;
	private Slot[] ring ;

	public RingBuffer(int size) {
		ring = new Slot[size];
		cursor = new AtomicInteger(0);
	}
}
