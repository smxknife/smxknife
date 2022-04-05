package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author smxknife
 * 2019/9/9
 */
public class LinkedTransferQueueDemo {
	public static void main(String[] args) {
		LinkedTransferQueue<Integer> queue = new LinkedTransferQueue();
		boolean b = queue.tryTransfer(1);
		System.out.println(b);
	}
}
