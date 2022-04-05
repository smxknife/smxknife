package com.smxknife.energy.clouds.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2021/5/19
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServer {
	public static void main(String[] args) {
		SpringApplication.run(AdminServer.class, args);
	}
}
