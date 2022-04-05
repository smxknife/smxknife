package com.smxknife.cloud.netflix.eureka.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/4/29
 */
@RestController
@RequestMapping("/index")
public class IndexController {

	@Value("${server.port}")
	private int port;

	@RequestMapping("name")
	public String name() {
		return "IndexName";
	}

	@RequestMapping("port")
	public String port() {
		return "MyPort is " + this.port;
	}
}
