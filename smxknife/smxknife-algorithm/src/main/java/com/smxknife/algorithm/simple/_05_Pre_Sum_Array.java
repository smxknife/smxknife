package com.smxknife.algorithm.simple;

/**
 * 前缀和数组
 * 使用场景：查询数组中两个索引之间的和
 * 结构：原数组（原始数据）、H数组（索引i的位置存储的是原数组中0～i的和）
 * @author smxknife
 * 2021/2/4
 */
public class _05_Pre_Sum_Array {
	public static void main(String[] args) {
		int[] array = {0, 1, 2};
		RangeSum2 rangeSum2 = new RangeSum2(array);

		System.out.println(rangeSum2.rangeSum(0, 2));
	}

	/**
	 * 前缀数组的实现
	 */
	static class RangeSum2 {
		private int[] preSum;

		public RangeSum2(int[] array) {
			int n = array.length;
			preSum = new int[n];
			preSum[0] = array[0];
			for (int i = 1; i < n; i++) {
				preSum[i] = array[i] + preSum[i - 1];
			}
		}

		public int rangeSum(int l, int r) {
			int n = preSum.length;
			return l == 0 ? preSum[r] : preSum[r] - preSum[l];
		}
	}

	/**
	 * 这是二维数组的实现，(i, j) 代表了从i到j的数的和
	 * 这种方式会浪费将近一半的空间，在数据量少，并且查询极为频繁的场合比RangeSum2更为适用
	 */
	static class RangeSum1 {

	}
}
