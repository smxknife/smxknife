package com.smxknife.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author smxknife
 * 2021/3/23
 */
public class _1_TwoSum {
	public static void main(String[] args) {
		int[] nums = {1, 3, 5, 6, 9, 10, 2};
		int target = 3;

		_1_Solution doubleTraverse = new _1_DoubleTraverse();
		print(doubleTraverse, nums, target);

		_1_HashMapDValue hashMapDValue = new _1_HashMapDValue();
		print(hashMapDValue, nums, target);
	}

	private static void print(_1_Solution solution, int[] nums, int target) {
		int[] ints = solution.towSum(nums, target);
		if (Objects.isNull(ints) || ints.length == 0) {
			System.out.println("没找到");
		} else {
			System.out.println(new StringJoiner(", ").add(ints[0] + "").add(ints[1] + ""));
		}
		System.out.println("-----------------------------");
	}
}

interface _1_Solution {
	int[] towSum(int[] nums, int target);
}

/**
 * HashMap差值
 * 一次遍历，用target - nums[i]，以差值为key，i为value存储在hashmap中，进行遍历的时候进行num[i]的值是否存在map中，
 * 如果存在返回[map.get(num[i]), i]
 */
class _1_HashMapDValue implements _1_Solution {

	@Override
	public int[] towSum(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int iVal = nums[i];
			int dVal = target - iVal;
			if (map.containsKey(iVal)) {
				return new int[] {map.get(iVal), i};
			} else {
				map.put(dVal, i);
			}
		}

		return null;
	}
}

/**
 * 暴力双层循环
 * 两层循环，第一层遍历从i开始元素，第二层从i+1开始遍历
 */
class _1_DoubleTraverse implements _1_Solution {

	@Override
	public int[] towSum(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[0];
	}
}


