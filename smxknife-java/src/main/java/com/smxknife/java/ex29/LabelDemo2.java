package com.smxknife.java.ex29;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/24
 */
public class LabelDemo2 {
	public static void main(String[] args) throws InterruptedException {
		retry:
		for (int i = 0; i < 100; i++) {
			System.out.println("i = " + i);
			for (int j = 0; j < 100; j++) {
				System.out.println("ij = " + i + "" + j);
				TimeUnit.SECONDS.sleep(1);
//				if (j % 5 == 1) continue retry;
				if (j % 10 == 1) break retry;
			}
		}
	}
}
