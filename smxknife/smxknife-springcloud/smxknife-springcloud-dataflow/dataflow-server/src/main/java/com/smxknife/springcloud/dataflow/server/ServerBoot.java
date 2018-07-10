package com.smxknife.springcloud.dataflow.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

@EnableDataFlowServer
@SpringBootApplication
public class ServerBoot {
	public static void main(String[] args) {
		SpringApplication.run(ServerBoot.class, args);
	}
}
