package com.smxknife.java2.thread.scheduledexecutorservice.demo05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2019/8/30
 */
@AllArgsConstructor
@Getter
@Setter
public class MyRunnable05 implements Runnable {

	String name;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " - name " + name);
	}
}
