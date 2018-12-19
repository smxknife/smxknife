package com.smxknife.log.java.demo03;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author smxknife
 * 2018-12-19
 */
public class Main {
	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(PerformLogTest.class.getName())
				.warmupIterations(5)
				.measurementIterations(5)
				.forks(1)
				.build();
		new Runner(options).run();
	}
}
