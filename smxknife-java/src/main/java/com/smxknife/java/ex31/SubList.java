package com.smxknife.java.ex31;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/30
 */
public class SubList {
	public static void main(String[] args) {
		List<String> stringList = new ArrayList<>();
		stringList.add("1");
		stringList.add("1");
		stringList.add("1");

		System.out.println(stringList.subList(0, 10));
	}
}
