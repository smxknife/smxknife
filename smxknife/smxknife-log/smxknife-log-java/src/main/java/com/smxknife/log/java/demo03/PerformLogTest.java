package com.smxknife.log.java.demo03;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.*;

/**
 * @author smxknife
 * 2018-12-19
 */
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class PerformLogTest {

	private static final Logger logger = Logger.getLogger(PerformLogTest.class.getName());

	@Param({"100", "1000", "10000"})
	int loopNum;

	int[] loopIdx;

	@Setup
	public void setup() {
		loopIdx = new int[loopNum];
		for (int i = 0; i < loopNum; i++) {
			loopIdx[i] = i;
		}

		try {
			logger.setLevel(java.util.logging.Level.FINEST);
			FileHandler fileHandler = new FileHandler("./perform.log");
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new Formatter() {
				@Override
				public String format(LogRecord record) {
					return record.getLevel().getName()
							+ " : " + record.getThreadID()
							+ " " + record.getSourceClassName()
							+ "." + record.getSourceMethodName()
							+ " " + record.getMessage()
							+ "\r\n";
				}
			});
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Benchmark
	public void test(Blackhole blackhole) {
		int tmp = 0;
		for (int i = 0; i < loopIdx.length; i++) {
			logger.finer("test " + i);
		}
	}
}
