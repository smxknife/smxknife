package com.smxknife.datastructure.msb.newer;

/**
 * @author smxknife
 * 2021/6/23
 */
public class _01_Print32 {
	public static void main(String[] args) {
		int num = 12345;
		//
		for (int i = 31; i >=0; i--) {
			System.out.print((num & (1 << i)) == 0 ? "0" : "1");
		}
	}
}
