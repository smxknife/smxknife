package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2019/10/8
 */
public class DoublePlusDemo {

	static int num1;
	static int num2;
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("idx: " + i + " num1 = " + num1F() + ", num2 = " + num2F());
		}

		System.out.println("从下面两个函数的字节码指令来看，区别就是dup的时机，\r\n" +
				"后加加采用的是，先dup，将栈顶的元素复制一份压入栈顶，再执行iadd操作，最后剩下的栈顶元素时原始值，\r\n" +
				"前加加采用的是，先执行iadd操作，得到加1之后的值，再执行dup操作，最后剩下的栈顶元素为加1之后的值");
	}

	public static int num1F() {
		/**
		 *          0: getstatic     #14                 // Field num1:I
		 *          3: dup
		 *          4: iconst_1
		 *          5: iadd
		 *          6: putstatic     #14                 // Field num1:I
		 *          9: ireturn
		 */
		return num1++;
	}

	public static int num2F() {
		/**
		 *          0: getstatic     #15                 // Field num2:I
		 *          3: iconst_1
		 *          4: iadd
		 *          5: dup
		 *          6: putstatic     #15                 // Field num2:I
		 *          9: ireturn
		 */
		return ++num2;
	}
}
