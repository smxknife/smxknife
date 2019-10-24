package com.smxknife.java2.thread.designpattern._10TwoPhaseTermination.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/26
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("main: BEGIN");

		try {
			CountupThread thread = new CountupThread();
			thread.start();

			TimeUnit.SECONDS.sleep(10);

			System.out.println("main: shutdownRequest");
			thread.shutdownRequest();

			System.out.println("main: join");
			thread.join();

		} catch (InterruptedException e) {

		}
		System.out.println("main: END");
	}
}
