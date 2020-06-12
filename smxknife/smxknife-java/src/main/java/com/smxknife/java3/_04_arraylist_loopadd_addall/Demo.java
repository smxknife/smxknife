package com.smxknife.java3._04_arraylist_loopadd_addall;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/5/6
 */
public class Demo {
	public static void main(String[] args) {
		int num = 1000000;

		List<Object> orgs = IntStream.iterate(0, idx -> idx + 1).limit(num)
				.boxed()
				.collect(Collectors.toList());


		System.out.println("init finish");
		//List<Object> forAdd =
				forAdd(orgs);
		//System.out.println(forAdd.size());

		//List<Object> addAll =
				addAll(orgs);
		//System.out.println(addAll.size());

	}

	private static List<Object> addAll(List<Object> orgs) {
		List<Object> list = orgs;
		System.out.println("list size = " + list.size());
		List<Object> temp = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		temp.addAll(list);
		System.out.println("addAll : " + Duration.between(now, LocalDateTime.now()).toMillis());
		return temp;
	}

	private static List<Object> forAdd(List<Object> orgs) {
		List<Object> list = orgs;
		System.out.println("list size = " + list.size());
		List<Object> temp = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		System.out.println("forAdd : " + Duration.between(now, LocalDateTime.now()).toMillis());
		return temp;
	}
}
