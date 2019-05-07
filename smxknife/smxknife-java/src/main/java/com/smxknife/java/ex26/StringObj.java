package com.smxknife.java.ex26;

import java.util.Arrays;

/**
 * @author smxknife
 * 2019-05-07
 */
public class StringObj {

	private String[] v = new String[Integer.MAX_VALUE / 100];

	public StringObj(String val) {
		Arrays.fill(this.v, val);
	}
}
