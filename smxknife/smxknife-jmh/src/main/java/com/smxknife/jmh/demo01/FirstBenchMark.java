package com.smxknife.jmh.demo01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class FirstBenchMark {

	@Benchmark
	public String strPlus() {
		String str = "";
		for (int i = 0; i < 10000; i++) {
			str += i;
		}
		return str;
	}

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(FirstBenchMark.class.getSimpleName())
				.forks(1)
				.warmupIterations(5)
				.measurementIterations(5)
				.build();
		new Runner(options).run();

	}
}
