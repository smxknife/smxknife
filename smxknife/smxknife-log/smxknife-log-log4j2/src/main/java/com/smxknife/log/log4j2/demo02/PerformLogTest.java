package com.smxknife.log.log4j2.demo02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018-12-19
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class PerformLogTest {

	private static Logger logger;

	@Param({"100", "1000", "10000"})
	int loopNum;

	int[] loopIdx;

	@Setup
	public void setup() {
		loopIdx = new int[loopNum];
		for (int i = 0; i < loopNum; i++) {
			loopIdx[i] = i;
		}
		logger = LogManager.getLogger(this.getClass().getSimpleName());
	}

	@Benchmark
	public void test(Blackhole blackhole) {
		int tmp = 0;
		for (int i = 0; i < loopIdx.length; i++) {
			logger.error("test " + i);
		}
	}
}
