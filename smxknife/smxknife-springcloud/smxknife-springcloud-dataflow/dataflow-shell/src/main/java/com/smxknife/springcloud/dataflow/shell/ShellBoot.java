package com.smxknife.springcloud.dataflow.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.shell.EnableDataFlowShell;

@EnableDataFlowShell
@SpringBootApplication
public class ShellBoot {
	public static void main(String[] args) {
		SpringApplication.run(ShellBoot.class, args);
	}
}