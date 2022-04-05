package com.smxknife.algorithm.demo02._01_min_stack;

import java.util.Stack;

/**
 * @author smxknife
 * 2021/2/19
 */
public class MinStack1 {

	/**
	 * 存放原始的压入顺序的数据
	 */
	private Stack<Integer> stackData;

	/**
	 * 存放每一步的最小值
	 */
	private Stack<Integer> stackMin;

	public MinStack1() {
		stackData = new Stack<>();
		stackMin = new Stack<>();
	}

	public static void main(String[] args) {

	}
}
