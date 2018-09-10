package com.smxknife.springcloud.netflix.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

//	@Autowired
//	EurekaClient eurekaClient;

	@Autowired
	DiscoveryClient discoveryClient;

	@RequestMapping("/")
	public String test() {
		return "hello, test";
	}

	@RequestMapping("/service/url")
	public String serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("user");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri().toString();
		}
		return null;
	}
}
