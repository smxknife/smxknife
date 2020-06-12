package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/27
 */
public class IntegerSwap {
	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(2);

		IntegerSwap swap = new IntegerSwap();
		swap.swap(i1, i2);
		System.out.println(i1 + "_" + i2);
	}

	private void swap(Integer i1, Integer i2) {
		Integer temp = i1;
		i1 = i2;
		i2 = temp;
	}
}
