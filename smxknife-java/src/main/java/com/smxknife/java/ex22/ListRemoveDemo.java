package com.smxknife.java.ex22;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2018-12-24
 */
public class ListRemoveDemo {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			strings.add("test" + i);
		}

		Thread addThread = new Thread(() -> {
			while (true) {
				System.out.println("add");
				strings.add("test" + Math.random());
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}

		});

		Thread removeThread = new Thread(() -> {
			while (true) {
				System.out.println("remove");
				strings.remove(strings.get(0));
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}

		});

		addThread.start();
		removeThread.start();
 	}
}


