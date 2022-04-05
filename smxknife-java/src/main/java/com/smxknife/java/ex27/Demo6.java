package com.smxknife.java.ex27;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-05-31
 */
public class Demo6 {
	public static void main(String[] args) {
		Stream<String> first = Stream.of("a", "b", "c");
		Stream<String> two = Stream.of("1", "2", "3");
		Stream<String> three = Stream.of("yi", "er", "san").parallel();
		Stream<String> four = Stream.of("一", "二", "三").parallel();

		Stream<String> first1 = Stream.of("a", "b", "c");
		Stream<String> two1 = Stream.of("1", "2", "3");
		Stream<String> three1 = Stream.of("yi", "er", "san").parallel();
		Stream<String> four1 = Stream.of("一", "二", "三").parallel();


		Stream<String> concat = Stream.concat(first, two);
		System.out.println(concat.isParallel());

		Stream<String> stringStream = Stream.of(first, two).flatMap(Function.identity());
		System.out.println(stringStream.isParallel());

		Stream<String> concat1 = Stream.concat(four, three);
		System.out.println(concat1.isParallel());

		Stream<String> stringStream1 = Stream.of(four, three).flatMap(Function.identity());
		System.out.println(stringStream1.isParallel());

		Stream<String> reduce = Stream.of(first1, two1, three1)
				.reduce(Stream.empty(), Stream::concat);
		System.out.println(reduce.isParallel());

	}
}
