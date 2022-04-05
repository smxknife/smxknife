package com.smxknife.java.ex29;

/**
 * @author smxknife
 * 2019/8/24
 */
public class IntegerBinaryShow {
	public static void main(String[] args) {
		System.out.println(Integer.SIZE);

		System.out.println("------ -1");
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-1 << 29));
		System.out.println(-1 << 29);

		System.out.println("------- 0");
		System.out.println(Integer.toBinaryString(0));
		System.out.println(Integer.toBinaryString(0 << 29));
		System.out.println(0 << 29);

		System.out.println("------ 1");
		System.out.println(Integer.toBinaryString(1));
		System.out.println(Integer.toBinaryString(1 << 29));
		System.out.println(1 << 29);

		System.out.println("------- 2");
		System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(2 << 29));
		System.out.println(2 << 29);

		System.out.println("------- 3");
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(3 << 29));
		System.out.println(3 << 29);

		System.out.println("---------------------");

		System.out.println(Integer.toBinaryString((1 << 29) - 1));

		System.out.println(Integer.toBinaryString(1 & -1));
		System.out.println(Integer.toBinaryString( 1 | 0));

		System.out.println("按位与0 测试");
		System.out.println(-1 & 0);  // 所有的数 按位与0 之后会变为0
		System.out.println(0 & 0);
		System.out.println(1 & 0);
		System.out.println(2 & 0);
		System.out.println(3 & 0);
		System.out.println(12345 & 0);

		// 无论正负，奇数变为1，偶数变为0
		System.out.println("按位与1 测试 --- 无论正负，奇数变为1，偶数变为0");
		System.out.println(-1 & 1); // NOTE: 并不是所有的与1进行按位与运算后会变成1
		System.out.println(0 & 1);
		System.out.println(1 & 1);
		System.out.println(2 & 1); // 2 与 1进行按位与之后就会变为0
		System.out.println(3 & 1);
		System.out.println(4 & 1);
		System.out.println(5 & 1);
		System.out.println(6 & 1);
		System.out.println(12345 & 1);

		// 所有数与0 进行 按位或操作后变为本身
		System.out.println("按位或0 测试 --- 所有数与0 进行 按位或操作后变为本身");
		System.out.println(-1 | 0);
		System.out.println(0 | 0);
		System.out.println(1 | 0);
		System.out.println(2 | 0);
		System.out.println(3 | 0);
		System.out.println(12345 | 0);

		// 按位或1 与 1 进行运算，如果是奇数保持不变，如果是偶数则加1
		System.out.println("按位或1 测试 -- 按位或1 与 1 进行运算，如果是奇数保持不变，如果是偶数则加1");
		System.out.println(-1 | 1);
		System.out.println(0 | 1);
		System.out.println(1 | 1);
		System.out.println(2 | 1);
		System.out.println(3 | 1);
		System.out.println(4 | 1);
		System.out.println(5 | 1);
		System.out.println(6 | 1);
		System.out.println(12345 | 1);

		System.out.println("按位或2 测试 --- 无特别的规律");
		System.out.println(-1 | 2);
		System.out.println(0 | 2);
		System.out.println(1 | 2);
		System.out.println(2 | 2);
		System.out.println(3 | 2);
		System.out.println(4 | 2);
		System.out.println(5 | 2);
		System.out.println(6 | 2);
		System.out.println(12345 | 2);


	}
}
