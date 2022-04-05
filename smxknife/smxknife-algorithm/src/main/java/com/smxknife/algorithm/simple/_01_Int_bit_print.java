package com.smxknife.algorithm.simple;

/**
 * @author smxknife
 * 2021/2/4
 */
public class _01_Int_bit_print {
	public static void main(String[] args) {

		print(1);
		print(-1);
		print(0);
		print(Integer.MAX_VALUE);
		print(Integer.MIN_VALUE);
		print(-Integer.MIN_VALUE); // 最小的负数取反+1后就是它自己
		print(Integer.MAX_VALUE + 1);
		System.out.println("-----------------");
		print(Integer.MIN_VALUE >> 1);
		print(-1024 >> 1);
		print(1024 >> 1);
		System.out.println("-----------------");
		print(Integer.MIN_VALUE >>> 1);
		print(-1024 >>> 1);
		print(1024 >>> 1);
		System.out.println("-----------------");
		int num = 5;
		print(num);
		print(-num);
		print(~num + 1); // 负数就是正数取反加1

		/**
		 * 负数：二进制取反加1，即补码
		 */
	}

	private static void print(int num) {
		for (int i = 31; i >= 0; i--) {
			System.out.print((num & (1 << i)) == 0 ? "0" : "1");
		}
		System.out.println("    " + num);
		System.out.println();
	}


}
