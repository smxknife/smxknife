package com.smxknife.java2._volatile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2021/5/18
 */
public class Container {
	private volatile List list = new ArrayList();

	public synchronized void add(Object object) {
		this.list.add(object);
	}

	public synchronized int size() {
		return this.list.size();
	}

	public static void main(String[] args) throws IOException {
		Container c = new Container();

		new Thread(() -> {
			while (true) {
				if (c.size() == 5) {
					break;
				}
			}
			System.out.println("jieshu");
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
//				try {
//					TimeUnit.SECONDS.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}

		}).start();



		System.in.read();
	}
}
