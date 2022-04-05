package com.smxknife.algorithm.simple;

import java.util.Objects;

/**
 * 选择排序
 * [1, 3, 5, 2, 0]
 * 先拿第一个元素与后面元素比较，找到最小值，与第一个元素进行交换
 * 然后从第二个元素开始与后面所有的元素比较，找到最小值，与第二个元素进行交换
 * ...
 * 这样就排好了
 * @author smxknife
 * 2021/2/4
 */
public class _02_SelectionSort extends AbstractSort {
	public static void main(String[] args) {
		_02_SelectionSort obj = new _02_SelectionSort();
		int[] array = {1, 3, 5, 2, 0};
		obj.sort(array);
	}

	@Override
	protected void sortImpl(int[] array) {
		if (Objects.isNull(array) || array.length < 2) {
			return;
		}

		int n = array.length;

		for (int i = 0; i < n; i++) {
			int minIdx = i;

			for (int j = i + 1; j < n; j++) {
				minIdx = array[j] < array[minIdx] ? j : minIdx;
			}
			swap(array, i, minIdx);

		}
	}

	private void exec(int[] array) {
		int n = array.length;
		int markMinIndex = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				markMinIndex = array[i] > array[j] ? j : markMinIndex;
			}
			swap(array, i, markMinIndex);
		}
	}
}
