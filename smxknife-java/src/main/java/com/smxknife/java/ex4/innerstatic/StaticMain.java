package com.smxknife.java.ex4.innerstatic;

import java.util.LinkedList;
import java.util.List;

public class StaticMain {

	public static void main(String[] args) {

		List<Integer> list = new LinkedList<>();

		((LinkedList<Integer>) list).addFirst(1);
	}
}
