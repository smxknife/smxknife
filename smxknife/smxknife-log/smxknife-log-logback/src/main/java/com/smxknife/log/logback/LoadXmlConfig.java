package com.smxknife.log.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author smxknife
 * 2018-12-19
 */
public class LoadXmlConfig {

	/**
	 * 自定义logback的xml配置文件并加载
	 * @param fileName
	 */
	public static void loadConfig(String fileName) {
		if (fileName == null || "".equals(fileName)) return;
		String resPath = System.getProperty("user.dir") + File.separator
				+ "src" + File.separator
				+ "main" + File.separator
				+ "resources" + File.separator;
		File file = new File(resPath + fileName);
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator joranConfigurator = new JoranConfigurator();
		joranConfigurator.setContext(loggerContext);
		loggerContext.reset();
		try {
			joranConfigurator.doConfigure(file);
		} catch (JoranException e) {
			e.printStackTrace();
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
	}
}
