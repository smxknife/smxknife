package com.smxknife.java.ex6.collectiontolist;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		Collection<Integer> collection = new ArrayList<>();
		collection.add(1);
		collection.add(1);
		collection.add(1);

		for (int i : collection) {
			System.out.println(i);
		}
	}
}
