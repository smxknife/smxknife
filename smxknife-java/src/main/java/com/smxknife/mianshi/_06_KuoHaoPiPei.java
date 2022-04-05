package com.smxknife.mianshi;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 括号匹配
 * @author smxknife
 * 2021/6/2
 */
public class _06_KuoHaoPiPei {
	public static void main(String[] args) {
		Map<String, String> kuohao = new HashMap<>();
		kuohao.put("[", "]");
		kuohao.put("{", "}");
		kuohao.put("(", ")");

		String exp = "{[(1+1)* (2*2)]}";
		Stack<String> kStack = new Stack<>();
		for (char c : exp.toCharArray()) {
			if (kuohao.containsKey(c)) {
				kStack.push(c+"");
			}
			if (kuohao.containsValue(c)) {
				final String peek = kStack.peek();
				if (kuohao.get(peek).equals(c)) {
					kStack.pop();
				} else {
					throw new IllegalArgumentException();
				}
			}
		}
		System.out.println(kStack);

	}
}
