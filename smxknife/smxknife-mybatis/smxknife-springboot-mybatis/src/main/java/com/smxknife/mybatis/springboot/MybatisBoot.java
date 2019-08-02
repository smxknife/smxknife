package com.smxknife.mybatis.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2019-07-18
 */
@MapperScan("com.smxknife.mybatis.springboot.dao")
@SpringBootApplication
public class MybatisBoot {

	public static void main(String[] args) {
		SpringApplication.run(MybatisBoot.class, args);
	}
}
