package com.smxknife.jmh.demo02;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018-12-19
 */
public class MyBenchmark {

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public int testM(MyState state) {
		return state.a + state.b;
	}
}
