package com.smxknife.algorithm.leetcode;

/**
 * 最长回文子串
 * @author smxknife
 * 2021/6/18
 */
public class _5_LongestPalindrome {
	static int maxV = 0;
	static int begin = 0;
	public static void main(String[] args) {
		final Solution solution = new Solution();
		final String str = solution.longestPalindrome("ac");
		System.out.println(str);
	}

	/*
	abbbc
	bbb
	si代表第i个元素，长度为len，回文子串有以下特征
	1）s.i = s.i + n -1
	2) s.i+1 = s.j+n-2，即回文子串依然是回文子串

	 */
	static class Solution {
		public String longestPalindrome(String s) {
			if (s.length() < 2) {
				return s;
			}

			boolean[][] dp = new boolean[s.length()][s.length()];
			for (int i = 0; i < s.length(); i++) {
				dp[i][i] = true;
			}

			final char[] chars = s.toCharArray();
			for (int max = 2; max <= chars.length; max++) {
				for (int i = 0; i < chars.length; i++) {
					int j = i + max - 1;

					if (j >= chars.length) {
						break;
					}
					if (chars[i] != chars[j]) {
						dp[i][j] = false;
					} else {
						if (j - i < 3) {
							dp[i][j] = true;
						} else {
							dp[i][j] = dp[i+1][j-1];
						}
					}



					if (dp[i][j] && (j -i + 1) > maxV) {
						maxV = j -i + 1;
						begin = i;
					}

				}
			}
			return s.substring(begin, begin + maxV);
		}
	}
}
