package com.smxknife.java2.collections;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author smxknife
 * 2018/11/21
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		List<TestDemo> srcList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			TestDemo testDemo = new TestDemo();
			testDemo.age = i;
			srcList.add(testDemo);
		}

		List<TestDemo> destList = new ArrayList<>(Arrays.asList(new TestDemo[srcList.size()]));
		System.out.println(destList.size());
		System.out.println(srcList.size());
		Collections.copy(destList, srcList);
		System.out.println(destList);

		destList.get(0).age = 10;

		System.out.println(srcList.get(0));
		System.out.println(destList.get(0));

		System.out.println("========================");

		List<TestDemo> demoList2 = new ArrayList<>();
		Collections.addAll(demoList2, new TestDemo[srcList.size()]);
		Collections.copy(demoList2, srcList);
		System.out.println(demoList2);
		demoList2.get(0).age = 100;

		System.out.println(srcList);
		System.out.println(demoList2);

	}
}

@Data
class TestDemo implements Cloneable {
	int age;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

