package com.smxknife.java.ex29;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CompleteFutureException {
	public static void main(String[] args) {

		List<CompletableFuture<Void>> futures = Stream.of(1, 2, 3, 4).map(val -> CompletableFuture.runAsync(() -> {
			System.out.println(val);
			if (val == 3) {
				throw new RuntimeException("xxx");
			}
		})).collect(Collectors.toList());

		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
		System.out.println("end");
	}
}
