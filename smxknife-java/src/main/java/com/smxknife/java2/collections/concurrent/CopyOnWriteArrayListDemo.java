package com.smxknife.java2.collections.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author smxknife
 * 2019/9/9
 */
public class CopyOnWriteArrayListDemo {
	public static void main(String[] args) {
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();
		new CopyOnWriteArrayList_GetArray();
	}
}
