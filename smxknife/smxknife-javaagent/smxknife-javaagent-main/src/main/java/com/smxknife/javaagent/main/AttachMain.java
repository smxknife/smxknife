package com.smxknife.javaagent.main;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/10
 */
public class AttachMain {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("attach main");
		}
	}
}
