package com.smxknife.log.java.demo01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author smxknife
 * 2018/9/12
 */
public class LevelDemo {
	public static void main(String[] args) {
		java.util.logging.Logger logger = Logger.getLogger("aaa");
//		logger.setLevel(Level.SEVERE);
//		logger.setLevel(Level.WARNING);
//		logger.setLevel(Level.INFO);
//		logger.setLevel(Level.CONFIG);
//		logger.setLevel(Level.FINE);
//		logger.setLevel(Level.FINER);
//		logger.setLevel(Level.FINEST);
		logger.setLevel(Level.ALL);
//		logger.setLevel(Level.OFF);
		loggingLevelTest(logger);
	}

	private static void loggingLevelTest(Logger logger) {
//		ConsoleHandler consoleHandler = new ConsoleHandler();
//		consoleHandler.setLevel(Level.ALL);
//		logger.addHandler(consoleHandler);

		System.out.println(">>>>>>>>>> " + logger.getLevel().getName());
		logger.info("info");
		logger.severe("severe");
		logger.config("config");
		logger.fine("fine");
		logger.finest("finest");
		logger.finer("finer");
		logger.warning("warning");
	}
}
