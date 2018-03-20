package com.smxknife.softmarket.usermgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.smxknife.softmarket")
public class UserMgrBoot {

	public static void main(String[] args) {
		SpringApplication.run(UserMgrBoot.class, args);
	}
}
