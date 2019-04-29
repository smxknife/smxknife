package com.smxknife.algorithm.recursive;

/**
 * @author smxknife
 * 2019-04-29
 */
public class Recursive {

	/**
	 * 阶乘
	 * @param max 最大数
	 * @return
	 */
	public static long factorial(int max) {
		if (max == 1) return 1;
		else return max * factorial(max - 1);
	}
}
