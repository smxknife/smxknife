package com.smxknife.st4.demo01;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2020/6/17
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("demo01/rule.temp");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String temp = reader.lines().collect(Collectors.joining("\r\n"));
		System.out.println(temp);

		STGroup string = new STGroupString(temp);
		ST rule = string.getInstanceOf("rule");
		List<String> names = Arrays.asList("lily", "lucy");
		rule.add("ruleName", names);
		rule.add("condition", "test");
		rule.add("then", "start");

		System.out.println(rule.render());

	}
}
