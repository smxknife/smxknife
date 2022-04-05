package com.smxknife.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2021/1/19
 */
@SpringBootApplication
@MapperScan("com.smxknife.mybatis.plus.mapper")
public class Boot {
	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
}
