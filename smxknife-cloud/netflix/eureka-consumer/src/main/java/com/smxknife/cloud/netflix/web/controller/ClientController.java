package com.smxknife.cloud.netflix.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author smxknife
 * 2021/4/29
 */
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	DiscoveryClient discoveryClient;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	/**
	 * 返回结果demo：{
	 *     ["eureka-provider","eureka-consumer"]
	 * }
	 * @return
	 */
	@RequestMapping("services")
	public List<String> getServices() {
		return discoveryClient.getServices();
	}

	/**
	 *
	 * @param serviceId demo：就像上面service取出的结果，如eureka-provider
	 * @return
	 */
	@RequestMapping("instances")
	public List<ServiceInstance> getInstances(String serviceId) {
		return discoveryClient.getInstances(serviceId);
	}

	@RequestMapping("loadbalance/instance")
	public ServiceInstance getInstance(String serviceId) {
		final ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
		return serviceInstance;
	}

	@RequestMapping("loadbalance/provider/index")
	public Object getProviderIndex() {

		final ServiceInstance instance = this.getInstance("eureka-provider");


		final URI uri = instance.getUri();
		System.out.println("provider url = " + uri);
		System.out.println("provider url getRawPath = " + uri.getRawPath());
		System.out.println("provider url getPath = " + uri.getPath());
		System.out.println("provider url getRawQuery = " + uri.getRawQuery());
		final RestTemplate restTemplate = new RestTemplate();

		final String indexName = restTemplate.getForObject(uri + "/index/name", String.class);

		return indexName;
	}


}
