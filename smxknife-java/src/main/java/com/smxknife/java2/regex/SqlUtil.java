package com.smxknife.java2.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2018/9/4
 */
public class SqlUtil {

	public static String camel2Hungary(String field) {
		char[] chars = field.toCharArray();
		Pattern pattern = Pattern.compile("([A-Z])|([0-9]+)");
		StringBuffer sb = new StringBuffer();
		Matcher matcher = pattern.matcher(field);
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group().toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
