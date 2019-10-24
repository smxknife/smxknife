package com.travelsky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author smxknife
 * 2019/10/18
 */
@SpringBootApplication
@ServletComponentScan
public class AirPortBoot {
	public static void main(String[] args) {
		SpringApplication.run(AirPortBoot.class, args);
	}
}
