package com.smxknife.servlet.springboot.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 测试启动顺序
 * @author smxknife
 * 2018-12-26
 */
@SpringBootApplication
@ServletComponentScan
public class Demo01App {

	public static void main(String[] args) {
		SpringApplication.run(Demo01App.class, args);
	}
}
