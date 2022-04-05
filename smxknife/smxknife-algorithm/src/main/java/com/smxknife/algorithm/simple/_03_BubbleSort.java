package com.smxknife.algorithm.simple;

import java.util.Objects;

/**
 * 冒泡排序
 * n个元素
 * 第一趟：从0到n-1，两两比较，前者比后者大则交换：找到最大值，在n-1的位置
 * 第二趟：从0到n-2，两两比较，前者比后者大则交换：找到剩下的最大值，在n-2的位置
 * ...
 * 一直到0
 * @author smxknife
 * 2021/2/4
 */
public class _03_BubbleSort extends AbstractSort {
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 2, 0};
		_03_BubbleSort obj = new _03_BubbleSort();
		obj.sort(array);
	}

	@Override
	protected void sortImpl(int[] array) {
		if (Objects.isNull(array) || array.length < 2) {
			return;
		}

		// 0 - n-1
		// 0 - n-2
		// 0 - n-3
		// ...
		int n = array.length;
		for (int end = n - 1; end >= 0; end--) {
			// idx: 0,1 1,2 2,3...
			// second 表示索引，两两比较的第二个元素，第二个元素会一直到end
			for (int second = 1; second <= end; second++) {
				if (array[second - 1] > array[second]) {
					swap(array, second - 1, second);
				}
			}
		}

	}

	private void exec(int[] array) {
		int n = array.length;
		for (int i = n-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j+1]) {
					swap(array, j, j+1);
				}
			}
		}
	}
}
