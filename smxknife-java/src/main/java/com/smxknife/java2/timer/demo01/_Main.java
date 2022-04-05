package com.smxknife.java2.timer.demo01;

import lombok.SneakyThrows;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/3/18
 */
public class _Main {
	public static void main(String[] args) {
		// 通过监控发现，默认的线程名是Timer-0
		Timer timer = new Timer("Test-Timer-%d");

		timer.schedule(new TimerTask() {
			@SneakyThrows
			@Override
			public void run() {
				TimeUnit.SECONDS.sleep(1);
			}
		}, 3000L, 2000L);
	}
}
