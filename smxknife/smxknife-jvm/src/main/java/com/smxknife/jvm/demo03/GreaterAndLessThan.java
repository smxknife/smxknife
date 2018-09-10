package com.smxknife.jvm.demo03;

public class GreaterAndLessThan {
	public static void main(String[] args) {
		GreaterAndLessThan than = new GreaterAndLessThan();
		than.greaterThan100(10);
		than.lessThan100(10);
	}

	public int lessThan100(double d) {
		if (d < 100.0) {
			return 1;
		} else {
			return -1;
		}
	}

	public int greaterThan100(double d) {
		if (d > 100.0) {
			return 1;
		} else {
			return -1;
		}
	}
}
