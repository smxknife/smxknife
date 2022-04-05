package com.smxknife.cloud.netflix.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author smxknife
 * 2021/4/29
 */
@RestController
@RequestMapping("/load/balance/ribbon")
public class RibbonLoadBalanceController {

	@Autowired
	LoadBalancerClient client;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	@Qualifier("loadBalancer")
	RestTemplate loadBalancerRestTemplate;

	@RequestMapping("provider/which")
	public String whichProvider() {
		final ServiceInstance serviceInstance = client.choose("eureka-provider");
		final URI uri = serviceInstance.getUri();
		return restTemplate.getForObject(uri + "/index/port", String.class);
	}

	@RequestMapping("provider/which2")
	public String whichProvider2() {
		// 这里直接通过RestTemplate进行调用，需要注意的是RestTemplate被@LoadBalanced修饰
		// url直接采用<协议>://<服务名>/<path>
		final String url = "http://eureka-provider/index/port";
		return loadBalancerRestTemplate.getForObject(url, String.class);
	}
}
