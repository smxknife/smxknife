package com.smxknife.algorithm.demo01.selectionsort;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * @author smxknife
 * 2019-04-29
 */
public class SelectionSort {

	/**
	 * 选择排序
	 * @param desc 是否是降序排列，null|false为升序，true为降序
	 * @param elements 需要排序的元素
	 * @param <T>
	 * @return 排序后的数组
	 */
	public static <T extends Comparable> List<T> sort(Boolean desc, T... elements) {
		Preconditions.checkNotNull(elements);

		List<T> sorted = new ArrayList<>(elements.length);
		List<T> org = new LinkedList<>();
		org.addAll(Arrays.asList(elements));

		while (org.size() > 0) {
			T target = null;
			if (Boolean.TRUE.equals(desc)) {
				target = findMax(org);
			} else {
				target = findMin(org);
			}
			sorted.add(target);
		}

		return sorted;
	}

	private static <T extends Comparable> T findMin(List<T> org) {
		T target = org.get(0);
		for (T t : org) {
			if (target.compareTo(t) > 0) target = t;
		}
		org.remove(target);
		return target;
	}

	private static <T extends Comparable> T findMax(List<T> org) {
		T target = org.get(0);
		for (T t : org) {
			if (target.compareTo(t) < 0) target = t;
		}
		org.remove(target);
		return target;
	}


}
