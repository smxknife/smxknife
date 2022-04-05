package com.smxknife.cloud.netflix;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2021/5/30
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class LcnTxManagerBoot {
	public static void main(String[] args) {
		SpringApplication.run(LcnTxManagerBoot.class, args);
	}
}
