package com.smxknife.log.logback.demo01;

import com.smxknife.log.logback.LoadXmlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author smxknife
 * 2018-12-20
 */
public class Main {
	public static void main(String[] args) {
		LoadXmlConfig.loadConfig("logback.demo01.xml");
		Logger logger = LoggerFactory.getLogger(Main.class);
		logger.info("test");
		logger.trace("test");
		logger.debug("test");
		logger.error("test");
		logger.warn("test");
	}
}
