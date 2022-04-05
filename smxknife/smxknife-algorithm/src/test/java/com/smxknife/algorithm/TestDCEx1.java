package com.smxknife.algorithm;

import com.smxknife.algorithm.demo01.quicksort.DCEx1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author smxknife
 * 2019-04-29
 */
public class TestDCEx1 {

	@Test
	public void sumIntTest() {
		int[] nums = {1, 2, 3, 4};
		Assertions.assertEquals(10, DCEx1.sumInt(nums));
	}
}
