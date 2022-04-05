package com.smxknife.mianshi;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 * @author smxknife
 * 2021/6/2
 */
public class _05_NoRepeatMaxSubStr {
	public static void main(String[] args) {
		String str1 = "abcabcbb";
		String str2 = "abbb";
		String str3 = "bbbb";

		System.out.println(str1 + " | " + noRepeatMaxSubStr(str1));
		System.out.println(str2 + " | " + noRepeatMaxSubStr(str2));
		System.out.println(str3 + " | " + noRepeatMaxSubStr(str3));
	}

	/**
	 * 解题思路：
	 * 遍历所有的字符，依次判断，如果集合中不存在则添加到集合中
	 * 如果已经存在，则与max字符串进行长度对比，如果max小，将集合中的串赋值给max，清空集合
	 * 最后max就是最长的
	 * @param str
	 * @return
	 */
	private static String noRepeatMaxSubStr(String str) {
		String max = "";
		List<Character> characters = new ArrayList<>();


		for (char c : str.toCharArray()) {
			if (!characters.contains(c)) {
				characters.add(c);
			} else {
				if (max.length() < characters.size()) {
					final StringBuilder builder = new StringBuilder();
					for (Character character : characters) {
						builder.append(character);
					}
					max = builder.toString();
					characters.clear();
				}
			}
		}
		return max;

	}
}
