package com.smxknife.algorithm;

import com.smxknife.algorithm.quicksort.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author smxknife
 * 2019-04-29
 */
public class TestQuickSort {

	@Test
	public void sortTest() {
		List<Integer> integers = Arrays.asList(3, 4, 1, 0, 12, 5);
		List<Integer> sort = QuickSort.sort(integers);
		List<Integer> res = Arrays.asList(0, 1, 3, 4, 5, 12);
		System.out.printf("integers equals res ? %s\r\n", integers.equals(res));
		System.out.printf("sort equals res ? %s\r\n", sort.equals(res));

		Assertions.assertIterableEquals(res, sort);
	}

	@Test
	public void sortObjTest() {
		List<People> peoples = Arrays.asList(
				new People(100, "java"),
				new People(20, "test"),
				new People(350, "hello"),
				new People(40, "world"));
		List<People> sort = QuickSort.sort(peoples);
		Collections.sort(peoples);
		Assertions.assertIterableEquals(peoples, sort);
	}

	@Test
	public void sortBadCaseTest() {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> sort = QuickSort.sort(integers);
		Assertions.assertIterableEquals(integers, sort);
	}
}
