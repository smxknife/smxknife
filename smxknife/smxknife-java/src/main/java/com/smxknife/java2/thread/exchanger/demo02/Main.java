package com.smxknife.java2.thread.exchanger.demo02;

import java.util.concurrent.Exchanger;

/**
 * @author smxknife
 * 2020/6/5
 */
public class Main {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		SingleThread thread = new SingleThread(exchanger);
		thread.setName("xxxxxx");
		thread.start();

	}
}
