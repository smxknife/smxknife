package com.smxknife.algorithm;

import com.smxknife.algorithm.binarysearch.BinarySearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2019-04-29
 */
public class TestBinarySearch {

	@Test
	public void findTest() {
		List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
		Integer target = 14;
		Assertions.assertEquals(14, BinarySearch.find(target, integers.toArray(new Integer[integers.size()])));
	}

	@Test
	public void findObjTest() {
		List<People> peoples = Arrays.asList(
				new People(10, "java"),
				new People(20, "test"),
				new People(30, "hello"),
				new People(40, "world"));
		People target = new People(30, "ll");
		Assertions.assertEquals(2, BinarySearch.find(target, peoples.toArray(new People[peoples.size()])));
	}

	@Test
	public void findStepTest() {
		List<Integer> els = new ArrayList<>(128);
		for (int i = 0; i < 128; i++) {
			els.add(i);
		}
		Assertions.assertEquals(100, BinarySearch.find(100, els.toArray(new Integer[els.size()])));
	}
}

