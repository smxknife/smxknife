package com.smxknife.java2.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author smxknife
 * 2019/9/1
 */
public class ArrayListDemo {
	public static void main(String[] args) {
		List<String> emptyList1 = new ArrayList<>(0);

		List<String> emptyList2 = new ArrayList<>(0);

		System.out.println(emptyList1);
		System.out.println(emptyList2);

		emptyList1.add(new String("a"));

		System.out.println(emptyList1);
		System.out.println(emptyList2);

		Object clone = ((ArrayList) emptyList1).clone();
		System.out.println(clone);

		ArrayList cloneList = (ArrayList) clone;
		for (int i = 0; i < cloneList.size(); i++) {
			// 输出为true，证明clone克隆的只是外部对象，没有对内部对象进行克隆 -- 浅拷贝
			System.out.println((cloneList.get(i) == emptyList1.get(i)) + " // 输出为true，证明clone克隆的只是外部对象，没有对内部对象进行克隆 -- 浅拷贝");
		}

		// 将原有的a进行覆盖
		cloneList.set(0, new String("a"));
		System.out.println(cloneList);
		for (int i = 0; i < cloneList.size(); i++) {
			// 输出为false，从另一方面验证了clone并没有clone集合内部的元素，只是将元素引用复制过来
			System.out.println((cloneList.get(i) == emptyList1.get(i)) + " // 输出为false，从另一方面验证了clone并没有clone集合内部的元素，只是将元素引用复制过来");
		}

		Collections.unmodifiableList(emptyList1);
		Collections.synchronizedList(emptyList1);
		Collections.checkedList(emptyList1, String.class);
	}
}
