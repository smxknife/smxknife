package com.smxknife.log.logback.demo02;

import com.smxknife.log.logback.LoadXmlConfig;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		LoadXmlConfig.loadConfig("logback.demo02.xml");
		logger = LoggerFactory.getLogger(PerformLogTest.class);
	}

	@Benchmark
	public void test(Blackhole blackhole) {
		int tmp = 0;
		for (int i = 0; i < loopIdx.length; i++) {
			logger.error("test " + i);
		}
	}
}
