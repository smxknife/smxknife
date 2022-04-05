package com.smxknife.mianshi;

import java.util.concurrent.Exchanger;

/**
 * 顺序打印A1B2...Z26
 * @author smxknife
 * 2021/5/18
 */
public class _01_JiaoChaPrint {
	public static void main(String[] args) {

		Exchanger<Object> exchanger = new Exchanger<>();
		final Thread t1 = new Thread(() -> {
			char begin = 'A';
			for (int i = begin; i < begin + 26; i++) {
				try {
					System.out.print((char) i);
					System.out.print(exchanger.exchange(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		final Thread t2 = new Thread(() -> {
			for (int i = 1; i < 27; i++) {
				//System.out.print(i);
				try {
					exchanger.exchange(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
	}
}
