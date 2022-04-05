package com.smxknife.java.ex31;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2020/8/24
 */
public class ParallelStreamDemo {
	public static void main(String[] args) {
		List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5).parallelStream().map(integer -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return integer;
		}).collect(Collectors.toList());
		System.out.println(collect);
	}
}
