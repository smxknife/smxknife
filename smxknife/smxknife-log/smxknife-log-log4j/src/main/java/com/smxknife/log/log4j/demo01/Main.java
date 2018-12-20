package com.smxknife.log.log4j.demo01;

import org.apache.log4j.Logger;

/**
 * @author smxknife
 * 2018-12-20
 */
public class Main {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Main.class);
		logger.info("test");
		logger.error("test");
		logger.warn("test");
		logger.trace("test");
		logger.fatal("test");
	}
}
