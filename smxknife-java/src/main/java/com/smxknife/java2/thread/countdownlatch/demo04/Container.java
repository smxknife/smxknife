package com.smxknife.java2.thread.countdownlatch.demo04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2021/5/18
 */
public class Container {

	List<Object> list =new ArrayList<>();

	public void add(Object o) {
		this.list.add(o);
	}

	public int size() {
		return this.list.size();
	}

	public static void main(String[] args) {
		Container container = new Container();
		CountDownLatch latch = new CountDownLatch(1);

		new Thread(() -> {
			if (container.size() != 5) {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("dao 5 le");
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				container.add(new Object());
				System.out.println("add " + i);
				if (i == 4) {
					latch.countDown();
				}
			}
		}).start();

	}

}
