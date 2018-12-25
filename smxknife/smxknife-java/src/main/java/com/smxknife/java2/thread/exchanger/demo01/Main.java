package com.smxknife.java2.thread.exchanger.demo01;

import java.util.concurrent.Exchanger;

/**
 * @author smxknife
 * 2018-12-24
 */
public class Main {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();

		ThreadA a = new ThreadA(exchanger);
		ThreadB b = new ThreadB(exchanger);

		a.start();
		b.start();

		System.out.println("main end");
	}
}
