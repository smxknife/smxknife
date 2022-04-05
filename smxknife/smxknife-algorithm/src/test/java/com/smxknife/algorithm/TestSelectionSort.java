package com.smxknife.algorithm;

import com.smxknife.algorithm.demo01.selectionsort.SelectionSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author smxknife
 * 2019-04-29
 */
public class TestSelectionSort {

	@Test
	public void sortTest() {
		List<Integer> integers = Arrays.asList(3, 4, 1, 0, 12, 5);
		List<Integer> sort = SelectionSort.sort(null, integers.toArray(new Integer[integers.size()]));
		List<Integer> res = Arrays.asList(0, 1, 3, 4, 5, 12);
		System.out.printf("integers equals res ? %s\r\n", integers.equals(res));
		System.out.printf("sort equals res ? %s\r\n", sort.equals(res));

		Assertions.assertIterableEquals(res, sort);
		Collections.reverse(res);
		Assertions.assertIterableEquals(res, SelectionSort.sort(true, integers.toArray(new Integer[integers.size()])));

	}

	@Test
	public void sortObjTest() {
		List<People> peoples = Arrays.asList(
				new People(100, "java"),
				new People(20, "test"),
				new People(350, "hello"),
				new People(40, "world"));

		People[] people = peoples.toArray(new People[peoples.size()]);
		List<People> sortAsc = SelectionSort.sort(null, people);
		System.out.println(sortAsc);
		Collections.sort(peoples);
		Assertions.assertIterableEquals(peoples, sortAsc);
	}
}
