package com.smxknife.log.log4j.demo02;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author smxknife
 * 2018-12-20
 */
public class Main {
	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(PerformLogTest.class.getSimpleName())
				.forks(1)
				.warmupIterations(5)
				.measurementIterations(5)
				.build();
		new Runner(options).run();
	}
}
