package com.smxknife.java2.collections.deque;

import com.smxknife.java2.unsafe.UnsafeWrapper;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayDeque;

/**
 * @author smxknife
 * 2019/9/2
 */
public class ArrayDequeDemo {
	public static void main(String[] args) throws NoSuchFieldException {
		ArrayDeque<String> deque = new ArrayDeque<>();
		/**
		 * 1）transient Object[] elements; 存储元素的数组
		 * 2）transient int head 头部元素索引
		 * 3）transient int tail 尾部元素索引，即下个元素添加位置
		 * 4）private static final int MIN_INITIAL_CAPACITY = 8 默认最小容量，必须是2的幂次
		 * 5）elements = new Object[16]; 在不指定容量的情况下，默认容量为16
		 */

		// region 初始化队列内容
		deque.add("aaa");
		deque.add("bbb");
		deque.add("ccc");
		deque.add("ddd");
		System.out.println(deque);
		// endregion

		// region 测试deque分配空间的操作
		int length = 16;
		System.out.println(length |= (length >>> 1)); // 这个操作就是 按位或等，相当于操作 length = 16 + 16 / 2^1 = 24
		length = 16;
		System.out.println(length |= (length >>> 2)); // 相当于length = 16 + 16 / 2^2 = 20
		// endregion

		// 上面说了容量必须是2的幂次，那么如果自定义容量是如何处理呢
		ArrayDeque customDeque = new ArrayDeque(9);
		Field elementsField = customDeque.getClass().getDeclaredField("elements");
		elementsField.setAccessible(true);
		Unsafe unsafe = UnsafeWrapper.unsafe();
		long fieldOffset = unsafe.objectFieldOffset(elementsField);
		Object[] object = (Object[]) unsafe.getObject(customDeque, fieldOffset);
		System.out.println("param = 9, real length = " + object.length); // param = 9, real length = 16
		/**
		 * initialCapacity |= (initialCapacity >>>  1);
		 * initialCapacity |= (initialCapacity >>>  2);
		 * initialCapacity |= (initialCapacity >>>  4);
		 * initialCapacity |= (initialCapacity >>>  8);
		 * initialCapacity |= (initialCapacity >>> 16);
		 */



		System.out.println("add -----------");
		// add调用了addLast操作
		// 添加元素不能为null，会报空指针
		// elements[tail] = e; 就是将元素添加在tail的位置
		// if ( (tail = (tail + 1) & (elements.length - 1)) == head)
		//      这个操作理解起来还是比较晦涩的，一定要有一个前提，element.length 是2的幂次，（这很重要）
		//      element.length - 1，就会变成二进制全为1的数，那么与之进行与运算相当于求余
		// 像上面的判断，如果tail + 1 == head了，就进行双倍扩容

		// region 模拟(tail = (tail + 1) & (elements.length - 1)，可以忽略
		int head = 0, size = 8;
		for (int i = 0; i < size; i++) {
			System.out.println((i + 1) & (size - 1));
			System.out.println(Integer.toBinaryString(i + 1));
			System.out.println(Integer.toBinaryString(size - 1));
		}
		// endregion

		deque.addFirst("0000");
		System.out.println(deque);

		boolean offerFirst = deque.offerFirst("a");
		System.out.println(offerFirst);
		System.out.println(deque);

		boolean ddddddddd = deque.offer("ddddddddd");
		System.out.println(ddddddddd);
		System.out.println(deque);

		String peek = deque.peek();
		System.out.println(peek);
		System.out.println(deque);

		String poll = deque.poll();
		System.out.println(poll);
		System.out.println(deque);

		String pop = deque.pop();
		System.out.println(pop);
		System.out.println(deque);

	}
}
