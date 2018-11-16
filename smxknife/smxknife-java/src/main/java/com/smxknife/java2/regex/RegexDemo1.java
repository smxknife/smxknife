package com.smxknife.java2.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2018/11/12
 */
public class RegexDemo1 {
	public static void main(String[] args) {
		String content = "0noobnoob01ab";
		String regexStr = "(^\\d)(.*noob.*)(\\d*)";
		System.out.println(Pattern.matches(regexStr, content));
		Pattern pattern = Pattern.compile(regexStr);
		Matcher matcher = pattern.matcher(content);
		System.out.println(matcher);
		System.out.println(matcher.groupCount());
		if (matcher.find()) {
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.start());
			System.out.println(matcher.start(2));
		}
	}
}
