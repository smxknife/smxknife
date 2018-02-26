package com.smxknife.springboot.v1.ex02;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class AppBanner implements Banner {
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
		out.append("================================\r\n")
				.append("------ ")
				.append(environment.getProperty("app.name") + "\r\n")
				.append(sourceClass.getName() + "\r\n")
				.append("================================").println();
	}
}
