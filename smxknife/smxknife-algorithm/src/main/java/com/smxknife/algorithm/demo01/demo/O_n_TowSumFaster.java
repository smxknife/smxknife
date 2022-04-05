package com.smxknife.algorithm.demo01.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 使用线性级别的算法，找出已排序数组中和为0的整数对的数量
 * @author smxknife
 * 2019-05-18
 */
public class O_n_TowSumFaster {

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
		for (int i = 0; i < orderData.length; i++) {
			System.out.println("time : " + i);
			if (Objects.nonNull(map.get(-1 * orderData[i]))) {
				count++;
			} else {
				map.put(orderData[i], orderData[i]);
			}
		}
		System.out.println(count);
	}
}
