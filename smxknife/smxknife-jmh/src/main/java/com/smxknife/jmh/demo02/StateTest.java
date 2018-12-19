package com.smxknife.jmh.demo02;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author smxknife
 * 2018-12-19
 */
public class StateTest {
	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(MyBenchmark.class.getName())
				.forks(1)
				.warmupIterations(2)
				.measurementIterations(2)
				.build();
		new Runner(options).run();
	}
}
