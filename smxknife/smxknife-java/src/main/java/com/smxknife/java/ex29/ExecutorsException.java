package com.smxknife.java.ex29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019-08-19
 */
public class ExecutorsException {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);

		service.execute(() -> {
			throw new RuntimeException("aaa");
		});


	}
}
