package com.smxknife.java.ex28;

/**
 * @author smxknife
 * 2019-06-24
 */
public class BTraceDemo {
	public static void main(String[] args) throws InterruptedException {
		BTraceDemo demo = new BTraceDemo();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(3000L);
			System.out.println("main : " + demo.add(i));
		}
	}

	public int add(int num) {
		return num+1;
	}
}
