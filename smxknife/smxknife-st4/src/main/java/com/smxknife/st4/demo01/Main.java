package com.smxknife.st4.demo01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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


	}
}
