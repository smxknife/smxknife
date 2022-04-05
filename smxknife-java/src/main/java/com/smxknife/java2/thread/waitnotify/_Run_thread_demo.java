package com.smxknife.java2.thread.waitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/18
 */
public class _Run_thread_demo {
	public static void main(String[] args) throws InterruptedException {
		Container container = new Container();

		new Thread(() -> {
			synchronized (container) {
				if (container.size() != 5) {
					try {
						container.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("ting le");
				container.notify();
			}


		}).start();

		TimeUnit.SECONDS.sleep(1);

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (container) {
					container.add(new Object());
					System.out.println("add " + i);
					if (i == 4) {
						container.notify();
						try {
							container.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();

	}

	private static class Container {
		List list = new ArrayList();

		void add(Object obj) {
			this.list.add(obj);
		}

		int size() {
			return list.size();
		}
	}
}
