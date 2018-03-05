package com.smxknife.springboot.v2.ex03.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("MyCommandLineRunner...");
		Arrays.asList(args).forEach(it -> {
			System.out.println(it);
		});
	}
}
