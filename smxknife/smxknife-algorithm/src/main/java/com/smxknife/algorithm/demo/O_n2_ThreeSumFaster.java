package com.smxknife.algorithm.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * O(n^2)平方级别的算法
 * @author smxknife
 * 2019-05-18
 */
public class O_n2_ThreeSumFaster {
	public static void main(String[] args) {
		int[] orderData = new int[10];
		orderData[0] = -11;
		orderData[1] = -1;
		orderData[2] = 1;
		orderData[3] = 3;
		orderData[4] = 11;
		orderData[5] = 12;
		orderData[6] = 15;
		orderData[7] = 21;
		orderData[8] = 31;
		orderData[9] = 41;

		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;

		for (int i = 0; i < orderData.length - 2; i++) {
			for (int j = i+1; j < orderData.length - 2; j++) {

			}
		}
	}
}
