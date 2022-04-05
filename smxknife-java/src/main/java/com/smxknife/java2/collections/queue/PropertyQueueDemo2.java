package com.smxknife.java2.collections.queue;

import java.util.PriorityQueue;

/**
 * @author smxknife
 * 2020/7/21
 */
public class PropertyQueueDemo2 {
	public static void main(String[] args) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue();
		priorityQueue.add(6);
		priorityQueue.add(3);
		priorityQueue.add(2);
		priorityQueue.add(5);
		priorityQueue.add(4);
		priorityQueue.add(1);
		System.out.println(priorityQueue);
	}
}
