package com.smxknife.java2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2018/11/12
 */
public class RecursionException {

	public static void main(String[] args) {

		Recursion test = new Recursion();
		System.out.println(test);

		List<Recursion> recursionList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			recursionList.add(new Recursion());
		}
		System.out.println(recursionList);
	}
}
