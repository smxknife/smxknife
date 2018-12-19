package com.smxknife.log.java.demo02;

import java.io.IOException;
import java.util.logging.*;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-18
 */
public class TestMain {
	public static void main(String[] args) {
		TestMain main = new TestMain();
		//main.test1();
		//main.test2();
		//main.test3();
		//main.test4();
		//main.test5();
		main.test6();
		//main.test7();
		//main.test8();
		//main.test9();

	}

	/**
	 * logger.logp
	 */
	private void test9() {
		Logger logger = Logger.getLogger("teset9");
		logger.info("info");
		logger.logp(Level.WARNING, this.getClass().getName(), "test9", "hello");
//		logger.logrb(Level.INFO);
	}

	/**
	 * Filter
	 */
	private void test8() {
		Logger logger = Logger.getLogger("test8");
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFilter(new Filter() {
			@Override
			public boolean isLoggable(LogRecord record) {
				return record.getLevel().intValue() > Level.FINE.intValue();
			}
		});
		consoleHandler.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				return ">>>>>>>" + record.getLoggerName() + " : " + record.getLevel() + " : " + record.getLevel().intValue() + "\r\n";
			}
		});
		logger.addHandler(consoleHandler);
		logger.severe("test8 SERVERE");
		logger.warning("test8 WARNING");
		logger.info("test8 INFO");
		logger.config("test8 CONFIG");
		logger.fine("test8 FINE");
		logger.finer("test8 FINER");
		logger.finest("test8 FINEST");
	}

	/**
	 * 格式化输出
	 */
	private void test7() {
		Logger logger = Logger.getLogger("test7");
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				return record.getLoggerName()
						+ " : " + record.getLevel()
						+ " : " + record.getThreadID()
						+ " : " + record.getMessage()
						+ " : " + record.getSourceMethodName()
						+ " : " + record.getSourceClassName()
						+ " : " + record.getMillis()
						+ " : " + record.getParameters()
						+ "\r\n";
			}
		});
		logger.addHandler(consoleHandler);
		logger.severe("test7 SERVERE");
		logger.warning("test7 WARNING");
		logger.info("test7 INFO");
	}

	/**
	 * Handler处理
	 */
	private void test6() {
		Logger logger = Logger.getLogger("test6");
//		logger.setLevel(Level.ALL);
		Logger logger1 = Logger.getLogger("test6");
		System.out.println(logger == logger1);

		// 控制台
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
		// 文件
		try {
			FileHandler fileHandler = new FileHandler("./test6.log", true);
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.severe("test6 SERVERE");
		logger.warning("test6 WARNING");
		logger.info("test6 INFO");
		logger.config("test6 CONFIG");
		logger.fine("test6 FINE");
		logger.finer("test6 FINER");
		logger.finest("test6 FINEST");

	}

	/**
	 * 日志的级别，默认只让打印info以下级别
	 */
	private void test5() {
		Logger.getGlobal().setLevel(Level.WARNING);
		Logger logger = Logger.getLogger("test5");
		//logger.setLevel(Level.SEVERE);
		//logger.setLevel(Level.WARNING);
		//logger.setLevel(Level.INFO);
		//logger.setLevel(Level.CONFIG);
		logger.severe("test5 SERVERE");
		logger.warning("test5 WARNING");
		logger.info("test5 INFO");
		logger.config("test5 CONFIG");
		logger.fine("test5 FINE");
		logger.finer("test5 FINER");
		logger.finest("test5 FINEST");
	}

	/**
	 * 简单测试一下国际化
	 * 访问回事message里面的信息，如果key不存在，直接当成message输出
	 */
	private void test4() {
		System.out.println(System.getProperty("user.language"));
		Logger logger = Logger.getLogger(this.getClass().getName(), "message");
		logger.log(Level.INFO, "name1");
		logger.log(Level.INFO, "name");
		split();
	}

	/**
	 * 本地化消息日志
	 * NOTO: 这里需要特别注意的是BUNDLE_NAME的命名不能带"."否则会找不到
	 */
	private void test3() {
		final String BUNDLE_NAME = "mybundle";
		Logger logger = Logger.getLogger(this.getClass().getName(), BUNDLE_NAME);
		String fileName = "测试文件";
		logger.log(Level.INFO, "logKey", fileName);
		split();
	}

	/**
	 * 消息带参数{0}
	 * 这并不能完全体现出它的优势，真正的优势是在做本地化的时候，看test3
	 */
	private void test2() {
		Logger logger = Logger.getLogger(this.getClass().getName());
		String fileName = "测试";
		String from = "地点";
		String message = "Unable to delete file {0} from {1}!";
		logger.log(Level.INFO, message, Stream.of(fileName, from).toArray());
		split();
	}

	/**
	 * 一个构造函数
	 */
	private void test1() {
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.info("hello");
		System.out.println(logger.getLevel());
		System.out.println(logger.getName());
		System.out.println(logger.getParent().getResourceBundleName());
		split();
	}

	private void split() {
		System.out.println("---------------------------");
	}
}
