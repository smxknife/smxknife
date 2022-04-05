package com.smxknife.java2.thread.semaphore.demo08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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

		Semaphore semaphore = new Semaphore(1);

		final Thread t2 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " start ");
			while (true) {
				try {
					if (container.size() == 5) {
						semaphore.acquire();
						System.out.println("dao 5 le");
						break;
					} else {
						continue;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}



			semaphore.release();
		}, "t2");

		final Thread t1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " start ");
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 10; i++) {
				container.add(new Object());
				System.out.println("add " + i);
				if (i == 4) {
					semaphore.release();

					// TODO 这里是重点，必须保证t1先acquire
					try {
						t2.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}


					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1");

		t1.start();
		t2.start();

	}
}
