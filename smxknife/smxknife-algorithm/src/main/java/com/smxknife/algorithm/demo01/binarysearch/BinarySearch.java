package com.smxknife.algorithm.demo01.binarysearch;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author smxknife
 * 2019-04-28
 */
@Slf4j
public class BinarySearch {

	public static <T extends Comparable> int find(T t, T... elements) {
		Preconditions.checkArgument(Objects.nonNull(elements) && elements.length >= 0);
//		if (log.isInfoEnabled()) log.info("elements = {}", elements.toString());
		int low = 0;
		int high = elements.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2; // 如果不是偶数，向下取整
//			if (log.isInfoEnabled()) log.info("low = {}, mid = {}, high = {}", low, mid, high);
			if (t.compareTo(elements[mid]) == 0) {
				return mid;
			} else if (t.compareTo(elements[mid]) > 0) {
				low = mid + 1;
				if (low > high) return -1;
			} else {
				high = mid;
				if (high < low) return -1;
			}
		}
		return -1;
	}

}
