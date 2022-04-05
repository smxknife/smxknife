package com.smxknife.energy.collector.core.driver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/12
 */
@Slf4j
@Component
public class DriverInitialService implements CommandLineRunner {

	@Autowired
	private DriverManager driverManager;

	@Override
	public void run(String... args) throws Exception {
		driverManager.init();
		driverManager.start();
	}
}
