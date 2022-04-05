package com.smxknife.springboot._05_session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author smxknife
 * 2021/3/9
 */
@SpringBootApplication
@ServletComponentScan
public class _05_Main {
	public static void main(String[] args) {
		SpringApplication.run(_05_Main.class, args);
	}
}
