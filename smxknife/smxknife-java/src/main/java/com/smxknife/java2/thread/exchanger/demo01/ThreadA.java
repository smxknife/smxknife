package com.smxknife.java2.thread.exchanger.demo01;

import java.util.concurrent.Exchanger;

/**
 * @author smxknife
 * 2018-12-24
 */
public class ThreadA extends Thread {

	private Exchanger<String> exchanger;

	public ThreadA(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			System.out.println("在线程A获得线程B的值=" + exchanger.exchange("线程A"));
			System.out.println("A end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
