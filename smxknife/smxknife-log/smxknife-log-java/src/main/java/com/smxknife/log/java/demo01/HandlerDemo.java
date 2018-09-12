package com.smxknife.log.java.demo01;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author smxknife
 * 2018/9/12
 */
public class HandlerDemo {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("0");

//		ConsoleHandler consoleHandler = new ConsoleHandler();
//		consoleHandler.setLevel(Level.ALL);
//		logger.addHandler(consoleHandler);

		try {
			FileHandler fileHandler = new FileHandler("./log");
			fileHandler.setLevel(Level.SEVERE);
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			FileHandler fileHandler = new FileHandler("./log2");
//			fileHandler.setLevel(Level.WARNING);
//			logger.addHandler(fileHandler);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		logger.info("test info");
		logger.warning("test warning");
		logger.severe("test serve");
	}
}
