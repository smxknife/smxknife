package com.smxknife.guava.option;

import com.google.common.base.Optional;
import lombok.extern.java.Log;

/**
 * @author smxknife
 * 2018/9/4
 */
@Log
public class Demo01 {

	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);
		System.out.println(possible.isPresent());
		System.out.println(possible.get());
		System.out.println(possible.or(10));
		System.out.println(possible.asSet());
//		possible.of(null);

		System.out.println("=========================");
		Optional<Object> absent = Optional.absent();
		System.out.println(absent.isPresent());
//		System.out.println(absent.get());
		System.out.println(absent.or("absent"));
		System.out.println(absent.orNull());
		System.out.println(absent.asSet());

		System.out.println("=========================");
		Optional<Object> nullable = Optional.fromNullable(null);
		System.out.println(nullable.isPresent());
//		System.out.println(nullable.get());
		System.out.println(nullable.or("null"));
		System.out.println(nullable.orNull());
		System.out.println(nullable.asSet());
	}
}
