package com.smxknife.threadpattern.future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/4/6
 */
@AllArgsConstructor
public class RealData implements Callable<String> {

	private String prefix;

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(5);
		return prefix + "_1234";
	}
}
