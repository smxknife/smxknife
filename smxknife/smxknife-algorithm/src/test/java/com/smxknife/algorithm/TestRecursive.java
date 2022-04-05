package com.smxknife.algorithm;

import com.smxknife.algorithm.demo01.recursive.Recursive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author smxknife
 * 2019-04-29
 */
public class TestRecursive {

	@Test
	public void factorialTest() {
		int max = 3;
		Assertions.assertEquals(6, Recursive.factorial(3));
	}
}
