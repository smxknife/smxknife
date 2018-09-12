package com.smxknife.log.java.demo01;

import java.io.IOException;
import java.util.logging.*;

/**
 * @author smxknife
 * 2018/9/12
 */
public class FormatterDemo {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("aa");

		try {
			FileHandler fileHandler = new FileHandler("./format.log");
			fileHandler.setFormatter(new MyLogFormatter());
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("bbbb");
	}
}

class MyLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		System.out.println(record.getLevel().getName());
		System.out.println(record.getLoggerName());
		System.out.println(record.getMessage());
		System.out.println(record.getMillis());
		System.out.println(record.getParameters());
		System.out.println(record.getSequenceNumber());
		System.out.println(record.getResourceBundleName());
		System.out.println(record.getSourceClassName());
		System.out.println(record.getSourceMethodName());
		System.out.println(record.getThreadID());
		System.out.println(record.getThrown());
		return record.getLevel() + " : " + record.getMessage();
	}
}
