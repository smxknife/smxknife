package com.smxknife.java.ex29;

/**
 * ~0>>>32
 * @author smxknife
 * 2019-08-20
 */
public class ZeroQuFanYouYi32Bit {


	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(~0));
		System.out.println("--------------------------");
		System.out.println(Integer.toBinaryString(31));
		System.out.println(Integer.toBinaryString(32));
		System.out.println(Integer.toBinaryString(33));
		// 上面三个输出看出，31~33这三个数的，后五位（最大值31，对应的二进制11111），
		// int类型的最大值就是2^31 - 1为什么不是32，是因为留出一位给符号位
		// 所以用二进制的后五位就可以表示出int的最大范围，下面会用到
		System.out.println("------------------------------");
		System.out.println(Integer.toBinaryString(1 << 31));
		// 上面1左移2^31后变成-1（10000000000000000000000000000000）
		// 所以在int进行运算的时候要格外注意数值范围，否则可能出现无法预料的结果
		System.out.println(Integer.toBinaryString(1 << 32));
		System.out.println(Long.toBinaryString(1 << 33));
		// 上面，但是左移32、33位会怎样，int总共32位，从结果来看，并没有无限左移，
		// 其实，jvm在进行左移时采用的是截取二进制后五位算法，将32、33转换为二进制之后，再截取后五位，
		// 对后五位永远在31的范围内，所以，永远不会超过int的范围
		System.out.println(Long.toBinaryString(1L << 32));
		System.out.println(Long.toBinaryString(1L << 33));
		// 上面用Long来表示就可以正常左移32，33位了，long是64位的
		System.out.println("-----------------------------");

		System.out.println(Long.toBinaryString(1 << 1));
		System.out.println(Long.toBinaryString(1 << 2));
		System.out.println(Long.toBinaryString(1 << 3));
		System.out.println(Long.toBinaryString(1 << 4));

		System.out.println(Integer.toBinaryString(-1 << 1));
		System.out.println(Integer.toBinaryString(-1 << 2));
		System.out.println(Long.toBinaryString(-1 << 3));
		System.out.println(Long.toBinaryString(-1 << 4));
		System.out.println("------------------------------");

		System.out.println(Long.toBinaryString(~0L >>> 32));
		System.out.println(Long.toBinaryString(~0L >> 32));
		// 上面>> 和 >>> 的区别是前者带符号右移，负值补1，正值补0，后者是无符号右移，高位补0

		System.out.println("开始测试 ----------------------------");
		System.out.println(Integer.toBinaryString(-4));
		System.out.println(Long.toBinaryString((~0L >>> 32) & (-4L)));
	}
}
