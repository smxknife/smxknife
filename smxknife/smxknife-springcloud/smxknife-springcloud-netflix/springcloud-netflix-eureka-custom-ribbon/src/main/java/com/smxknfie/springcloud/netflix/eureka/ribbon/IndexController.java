package com.smxknfie.springcloud.netflix.eureka.ribbon;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2018/9/5
 */
@RestController
@RequestMapping("/custom/ribbon")
@Log
public class IndexController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@GetMapping("/user")
	public Object getUser() {
		return restTemplate.getForObject("http://user-service/user", String.class);
	}

	@GetMapping("/move")
	public Object getMove() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
		log.info(serviceInstance.getHost());
		log.info(String.valueOf(serviceInstance.getPort()));
		log.info(serviceInstance.getUri().toString());
		return serviceInstance;
	}
}
