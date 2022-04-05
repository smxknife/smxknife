package com.smxknife.datastructure.msb.newer;

/**
 * 在一个无序数组 arr[]  中查找局部最小
 * 特点：
 *      arr[0] < arr[1]，
 *      arr[n-2] > arr[n -1] ,
 *      连续的两个点数据是不会相等
 *      这三个条件的特点就是，两个端点是上扬的，所以一定会在中间一个最小
 * 采用二分：
 *      如果中间
 * @author smxknife
 * 2021/6/23
 */
public class _02_LocalMin {
	public static void main(String[] args) {
		int[] arr = new int[] {3,2,3,2,3};

		int idx = oneMinIndex(arr);
		System.out.println(idx);
	}

	private static int oneMinIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return  -1;
		}

		if (arr.length == 1 ) {
			return 0;
		}

		if (arr.length == 2) {
			return arr[0] > arr[1] ? 0 : 1;
		}

		int L = 0;
		int R = arr.length - 1;
		int mid = (L + R) / 2;
		while (L <= R) {
			if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
				return mid;
			}
			if (arr[mid] > arr[mid - 1]) {
				R = mid;
			} else {
				L = mid;
			}
			mid = (L + R) / 2;
		}
		return mid;
	}
}
