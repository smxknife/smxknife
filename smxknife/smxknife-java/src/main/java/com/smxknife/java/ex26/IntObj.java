package com.smxknife.java.ex26;

import java.util.Arrays;

/**
 * @author smxknife
 * 2019-05-07
 */
public class IntObj {

	private int[] v = new int[Integer.MAX_VALUE / 100];

	public IntObj(int val) {
		Arrays.fill(this.v, val);
	}
}
