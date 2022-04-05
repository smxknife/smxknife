package com.smxknife.cloud.netflix;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2021/5/30
 */
@SpringBootApplication
@EnableDistributedTransaction
public class LcnTxPayBoot {
	public static void main(String[] args) {
		SpringApplication.run(LcnTxPayBoot.class, args);
	}
}
