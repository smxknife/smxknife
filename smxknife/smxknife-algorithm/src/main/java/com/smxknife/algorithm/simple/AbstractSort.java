package com.smxknife.algorithm.simple;

/**
 * @author smxknife
 * 2021/2/4
 */
public abstract class AbstractSort {

	public final void sort(int[] array) {
		printArray(array);
		sortImpl(array);
		printArray(array);
	}

	protected abstract void sortImpl(int[] array);

	protected void swap(int[]array, int i, int j) {
		if (i == j) {
			return;
		}
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	protected static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}
