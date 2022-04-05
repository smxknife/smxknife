package com.smxknife.java2.string;

/**
 * 原理1：当使用任何方式来创建一个字符串对象s时，Java运行时（运行中JVM）会拿着这个s在String池中找是否存在内容相同的字符串对象，如果不存在，则在池中创建一个字符串s，否则，不在池中添加。
 *
 * 原理2：Java中，只要使用new关键字来创建对象，则一定会（在堆区或栈区）创建一个新的对象。
 *
 * 原理3：使用直接指定或者使用纯字符串串联来创建String对象，则仅仅会检查维护String池中的字符串，池中没有就在池中创建一个，有则罢了！但绝不会在堆栈区再去创建该String对象。
 *
 * 原理4：使用包含变量的表达式来创建String对象，则不仅会检查维护String池，而且还会在堆栈区创建一个String对象。最后指向堆内存中的对象
 * @author smxknife
 * 2019/9/1
 */
public class A_Equal_A {
	public static void main(String[] args) {
//		String a1 = "a";
//		String a2 = "a";
//		System.out.println(a1 == a2);

		// 下面是 a1 == a2的判断过程
		/**
		 *  0: ldc           #2                  // String a
		 *  2: astore_1
		 *  3: ldc           #2                  // String a
		 *  5: astore_2
		 *  6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 *  9: aload_1
		 * 10: aload_2
		 * 11: if_acmpne     18
		 */
		// 从上面看，a1和a2都是指向常量池的引用地址#2，这个引用只有一个

//		String b1 = new String("b");
//		String b2 = new String("b");
//		System.out.println(b1 == b2);

		/**
		 *  0: new           #2                  // class java/lang/String
		 *  3: dup
		 *  4: ldc           #3                  // String b
		 *  6: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
		 *  9: astore_1
		 * 10: new           #2                  // class java/lang/String
		 * 13: dup
		 * 14: ldc           #3                  // String b
		 * 16: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
		 * 19: astore_2
		 * 20: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 * 23: aload_1
		 * 24: aload_2
		 * 25: if_acmpne     32
		 */
		// 从这个结果来看，通过new关键字创建了两个对象，分别对这两个对象的引用，而不是指向常量池的的引用进行判断，所以false

//		String c1 = "c" + "c";
//		String c2 = "cc";
//		System.out.println(c1 == c2);
		/**
		 *  0: ldc           #2                  // String cc
		 *  2: astore_1
		 *  3: ldc           #2                  // String cc
		 *  5: astore_2
		 *  6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 *  9: aload_1
		 * 10: aload_2
		 * 11: if_acmpne     18
		 */
		// 从结果上看，直接从池中获取cc的对象，所以，最后c1==c2

//		String d1 = "d";
//		String d2 = new String("d");
//		System.out.println(d1 == d2); // false
//		System.out.println(d1 == d2.intern()); // true intern返回的是常量池中的引用

		String e1 = "e";
		String e2 = "ee";
		String e3 = "e" + e1; // 这里相当于new String("ee");
		System.out.println(e2 == e3); // false
	}
}
