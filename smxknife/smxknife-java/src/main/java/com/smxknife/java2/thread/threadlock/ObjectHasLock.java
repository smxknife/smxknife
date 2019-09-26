package com.smxknife.java2.thread.threadlock;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ObjectHasLock {

	private String name;
	private String attr;
	private Object lock = new Object();

	public String getName() {
		System.out.println(Thread.currentThread().getName() + " getName | thread has lock ? " + Thread.holdsLock(this));
		return name;
	}

	public void setName(String name) {
		System.out.println(Thread.currentThread().getName() + " setName | thread has lock ? " + Thread.holdsLock(this));
		this.getAttr();
		synchronized (lock) {
			this.name = name;
		}
	}

	public String getAttr() {
		System.out.println(Thread.currentThread().getName() + " getAttr | thread has lock ? " + Thread.holdsLock(this));
		return attr;
	}

	public synchronized void setAttr(String attr) {
		this.attr = attr;
	}

}
