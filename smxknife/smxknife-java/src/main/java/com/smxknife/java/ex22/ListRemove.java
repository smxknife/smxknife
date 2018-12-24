package com.smxknife.java.ex22;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2018-12-24
 */
public class ListRemove {
	public static void main(String[] args) {
		List<Integer> strings = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			strings.add(i);
		}

//		strings.forEach(it -> {
//			if (it % 2 == 0) strings.remove(it);
//		});

		System.out.println(strings);

//		for (int i = 0; i < strings.size(); i++) {
//			Integer it = strings.get(i);
//			if (it % 2 == 0) strings.remove(it);
//		}
//		System.out.println(strings);

		for (int i = strings.size() - 1; i >= 0; i--) {
			Integer it = strings.get(i);
			if (it % 2 == 0) strings.remove(it);
		}
		System.out.println(strings);
	}
}
