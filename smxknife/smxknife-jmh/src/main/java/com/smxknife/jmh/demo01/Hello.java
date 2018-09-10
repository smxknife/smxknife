package com.smxknife.jmh.demo01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class Hello {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(Hello.class.getSimpleName())
				.forks(1)
				.warmupIterations(3)
				.measurementIterations(3)
				.build();

		new Runner(options).run();
	}

	@Benchmark
	public void benchmarkTargetMethod() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
