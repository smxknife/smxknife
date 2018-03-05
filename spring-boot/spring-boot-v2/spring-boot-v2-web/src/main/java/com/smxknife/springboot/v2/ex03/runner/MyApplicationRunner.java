package com.smxknife.springboot.v2.ex03.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("MyApplicationRunner...");
		System.out.println(args.getOptionValues("abc"));
		args.getNonOptionArgs().forEach(it -> {
			System.out.println(it);
		});
		System.out.println("========");
		Arrays.asList(args.getSourceArgs()).forEach(it -> {
			System.out.println(it);
		});
	}
}
