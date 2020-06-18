package com.smxknife.drools.demo02;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author smxknife
 * 2020/6/15
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.smxknife.drools.demo02.dao")
@Slf4j
public class DroolsShopping {
	public static void main(String[] args) {
		SpringApplication.run(DroolsShopping.class, args);
		log.info("Start Success!");
	}
}
