package com.smxknife.algorithm.simple;

/**
 * @author smxknife
 * 2021/2/4
 */
public class _06_MathRandom {
	public static void main(String[] args) {
		System.out.println("测试开始");
		int testTimes = 1000000;
		int count = 0;
		for (int i = 0; i < testTimes; i++) {
			if (Math.random() < 0.7) {
				count++;
			}
		}
		System.out.println((double) count / (double) testTimes);
		System.out.println("====== 从上面的输出来看，Math.random除了能随机输出一个[0,1)的数，而且还能做到等概率输出");

		System.out.println("===========乘以一个数之后依然是等概率===================");
		count = 0;
		for (int i = 0; i < testTimes; i++) {
			if (Math.random() * 8 < 4) {
				count++;
			}
		}
		System.out.println((double) count / (double) testTimes);
		System.out.println("乘以8之后，变成[0, 8)的随机一个数，等概率");

		System.out.println("乘以一个数k后取整，就会变成[0, k-1]的整数");

		int k = 9;

		int[] counts = new int[k]; // counts[0]：0出现的次数，counts[1]：1出现的次数...
		for (int i = 0; i < testTimes; i++) {
			int ans = (int)(Math.random() * k);
			counts[ans]++;
		}
		for (int i = 0; i < k; i++) {
			System.out.println(i + "这个数出现了 " + counts[i] + " 次");
		}

		System.out.println("总结：Math.random出现的概率是等概率的，如果该概率修改为x的平方");

		System.out.println("-----------------------------------------");
		count = 0;
		double x = 0.5;
		for (int i = 0; i < testTimes; i++) {
			if (xToXPower2() < 0.5) {
				count++;
			}
		}
		double v = (double) count / (double) testTimes;
		System.out.println(x + " 出现的概率是 ：" + v);

	}

	/**
	 * 返回[0,1)
	 * 任意x，属于该范围，概率为x^2
	 * @return
	 */
	public static double xToXPower2() {
		return Math.max(Math.random(), Math.random());
	}
}
