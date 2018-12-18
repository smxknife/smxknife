package com.smxknife.java.ex21;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author smxknife
 * 2018-12-14
 */
public class TimerDemo {
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				timer.cancel();
				for (int i = 0; i < 10; i++) {
					System.out.println("hhh");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0L, 3 * 1000L);
	}
}
