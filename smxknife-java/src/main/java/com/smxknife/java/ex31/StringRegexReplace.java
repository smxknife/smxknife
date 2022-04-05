package com.smxknife.java.ex31;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2020/7/3
 */
public class StringRegexReplace {
	public static void main(String[] args) {
		String metric = "00-00-0000-013300-11";
		String regex = "[0-9]{4}";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(metric);

		if (matcher.find()) {
			System.out.println(" | " + matcher.group(0));
		}

		System.out.println(metric.replaceAll("-\\d{4}-", "-"));
	}
}
