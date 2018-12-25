package com.smxknife.java2.thread.exchanger.demo01;

import java.util.concurrent.Exchanger;

/**
 * @author smxknife
 * 2018-12-24
 */
public class ThreadB extends Thread {

	private Exchanger<String> exchanger;

	public ThreadB(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			System.out.println("在线程B获得线程A的值=" + exchanger.exchange("线程B"));
			System.out.println("B end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
