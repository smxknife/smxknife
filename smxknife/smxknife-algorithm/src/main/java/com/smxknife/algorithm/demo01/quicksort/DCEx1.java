package com.smxknife.algorithm.demo01.quicksort;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * sum递归求和
 * @author smxknife
 * 2019-04-29
 */
public class DCEx1 {

	public static int sumInt(int ...nums) {
		Preconditions.checkNotNull(nums);
		int length = nums.length;
		if (length == 1) return nums[0]; // base condition
		else return nums[length - 1] + sumInt(Arrays.copyOf(nums, length - 1));
	}

	public static long sumLong(long ...nums) {
		Preconditions.checkNotNull(nums);
		int length = nums.length;
		if (length == 1) return nums[0]; // base condition
		else return nums[length - 1] + sumLong(Arrays.copyOf(nums, length - 1));
	}

	public static float sumFloat(float ...nums) {
		Preconditions.checkNotNull(nums);
		int length = nums.length;
		if (length == 1) return nums[0]; // base condition
		else return nums[length - 1] + sumFloat(Arrays.copyOf(nums, length - 1));
	}

	public static double sumDouble(double ...nums) {
		Preconditions.checkNotNull(nums);
		int length = nums.length;
		if (length == 1) return nums[0]; // base condition
		else return nums[length - 1] + sumDouble(Arrays.copyOf(nums, length - 1));
	}
}
