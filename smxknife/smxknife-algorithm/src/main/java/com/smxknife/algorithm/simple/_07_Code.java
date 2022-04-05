package com.smxknife.algorithm.simple;

import java.util.function.Supplier;

/**
 * @author smxknife
 * 2021/2/4
 */
public class _07_Code {
	public static void main(String[] args) {
		// b方案
		System.out.println("测试f()的等概率性");
		check(() -> f(), 1);
		System.out.println("测试f1()的等概率性");
		check(() -> f1(), 0);
		System.out.println("测试f2()的等概率性");
		check(() -> f2(), 0);
		System.out.println("测试f3()的等概率性");
		check(() -> f3(), 6);
		System.out.println("测试f4()的等概率性");
		check(() -> f4(), 7);

		for (int i = 0; i < 10; i++) {
			System.out.println(b());
		}
	}

	private static int b() {
		return f4();
	}

	/**
	 * f3 + 1 得到1到7等概率
	 * @return
	 */
	private static int f4() {
		return f3() + 1;
	}

	/**
	 * 根据f2，算出0到6等概率
	 * @return
	 */
	private static int f3() {
		int ans = 0;
		do {
			ans = f2();
		} while (ans == 7);
		return ans;
	}

	/**
	 * 根据f1算出等概率的f2，从0到7
	 * @return
	 */
	private static int f2() {
		return (f1() << 2) + (f1() << 1) + f1();
	}

	/**
	 * 根据f()，等概率返回0和1的函数
	 * @return
	 */
	private static int f1() {
		int ans = 0;
		do {
			ans = f();
		} while (ans == 3);
		return ans < 3 ? 0 : 1;
	}

	/**
	 * 验证出现1等概率
	 */
	private static final void check(Supplier<Integer> supplier, int target) {
		int count = 0;
		int times = 1000000;
		for (int i = 0; i < times; i++) {
			if (supplier.get().intValue() == target) {
				count++;
			}
		}
		double v = (double) count / (double) times;
		System.out.println("-- " + target + " 出现的概率为：" + v);
	}


	/**
	 * f()不可更改，等概率返回1到5的整数
	 * @return
	 */
	public static final int f() {
		return (int) (Math.random() * 5) + 1;
	}
}
