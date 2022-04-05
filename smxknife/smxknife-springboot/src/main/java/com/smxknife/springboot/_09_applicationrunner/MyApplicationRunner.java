package com.smxknife.springboot._09_applicationrunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/8/1
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("-------------------------------- \r\n" + args.getOptionNames());
	}
}
