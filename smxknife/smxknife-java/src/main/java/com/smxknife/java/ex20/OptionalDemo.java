package com.smxknife.java.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author smxknife
 * 2018/11/22
 */
public class OptionalDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		Optional<String> s = Optional.of("");
		s.ifPresent(list::add);
	}
}
