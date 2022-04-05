package com.smxknife.java2.thread.designpattern._03GuardedSuspension.demo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用于存放请求
 * @author smxknife
 * 2019/9/24
 */
public class RequestQueue {

	private final Queue<Request> queue = new LinkedList<>();

	/**
	 * 取出最先放入队列中的请求，作为其返回值。如果一个请求都没有，那就一直等待，直到其他线程putRequest
	 * @return
	 */
	public synchronized Request getRequest() {
		while (queue.peek() == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return queue.remove();
	}

	/**
	 * 添加请求
	 * @param request
	 */
	public synchronized void putRequest(Request request) {
		queue.offer(request);
		notifyAll();
	}
}
