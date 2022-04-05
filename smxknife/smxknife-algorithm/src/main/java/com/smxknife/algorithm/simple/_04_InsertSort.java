package com.smxknife.algorithm.simple;

import java.util.Objects;

/**
 * 插入排序
 * 理解方式是，斗地主摸牌，手里的牌是引进排好顺序，
 * 新牌假设放在最后面，然后往前看，发现前面的牌比新牌大，那么新牌往前移动一个位置，
 * 继续往前看，前面的牌是否比新牌大，如果大，继续将新牌往前移动，
 * 直到发现前面的牌比新牌小，那么新牌就放在当前位置，
 * 因为前面的牌肯定是排好顺序的，只要前一张小，那么前面所有的牌肯定要小于新牌
 * @author smxknife
 * 2021/2/4
 */
public class _04_InsertSort extends AbstractSort {
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 2, 0};
		_04_InsertSort obj = new _04_InsertSort();
		obj.sort(array);
	}

	@Override
	protected void sortImpl(int[] array) {
		if (Objects.isNull(array) || array.length < 2) {
			return;
		}

		// 每次从0开始，第一次0和0进行排序，第二次0到1进行排序，第三次0到2进行排序
		// 0 - 0
		// 0 - 1
		// 0 - 2
		// 0 - n-1
		int n = array.length;
		for (int end = 1; end < n; end++) {
			int newIdx = end;
			while (newIdx - 1 >= 0 && array[newIdx - 1] > array[newIdx]) {
				swap(array, newIdx - 1, newIdx);
				newIdx--;
			}
		}

		//exec(array);
	}

	// TODO 下面这是一个错误的写法
	private void exec(int[] array) {
		int n = array.length;

		// 从1开始表示最左侧已经排好顺序
		for (int i = 1; i < n; i++) {
			int insertIndex = i;
			int value = array[insertIndex];
			printArray(array);
			while ((insertIndex - 1 >= 0) && array[insertIndex - 1] > value) {
				//swap(array, insertIndex - 1, insertIndex);
				insertIndex--; // 这里要注意一下，虽然insertIndex前面的元素是排好顺序的，但是insertIndex应该插入到哪个位置还不确定，所以要遍历
			}

			// 错误的点在这里，虽然前面的序列是已经排好的顺序，所以找到第一个比他大的数就可以，但是找到之后必须使用插入方法，不能采用下面的交换，交换会导致已经被排好序的元素交换出去，所以如果要采用交换的方式，还必须两两交换
			if (insertIndex < i) {
				swap(array, insertIndex, i);
			}
		}
	}
}
