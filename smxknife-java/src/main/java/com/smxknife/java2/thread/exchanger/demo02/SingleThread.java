package com.smxknife.java2.thread.exchanger.demo02;

import lombok.SneakyThrows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/5
 */
public class SingleThread extends Thread {
	private Exchanger<String> exchanger;

	public SingleThread(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			String xxxxxxxxx = exchanger.exchange("xxxxxxxxx");
			System.out.println("singleThread 获取值 = " + xxxxxxxxx);
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
