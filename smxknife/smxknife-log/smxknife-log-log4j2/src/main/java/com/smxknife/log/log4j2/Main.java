package com.smxknife.log.log4j2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author smxknife
 * 2018-12-20
 */
public class Main {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Main.class);
		logger.info("test");
		logger.error("test");
		logger.debug("test");
		logger.warn("test");
		logger.fatal("test");
	}
}
