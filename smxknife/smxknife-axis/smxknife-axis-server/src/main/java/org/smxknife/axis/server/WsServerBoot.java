package org.smxknife.axis.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author smxknife
 * 2019/10/18
 */
@SpringBootApplication
@ServletComponentScan
public class WsServerBoot {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WsServerBoot.class, args);
	}
}
