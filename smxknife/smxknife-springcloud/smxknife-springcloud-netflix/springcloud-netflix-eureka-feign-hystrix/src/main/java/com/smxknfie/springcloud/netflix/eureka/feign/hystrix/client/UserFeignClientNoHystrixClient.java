package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client;

import com.smxknfie.springcloud.netflix.eureka.feign.config.UserFeignClientNoHystrixClientConfiguration;
import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.fallback.UserFeignClientNoHystrixClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author smxknife
 * 2018/9/9
 */
@FeignClient(name = "user-service", configuration = UserFeignClientNoHystrixClientConfiguration.class,fallback = UserFeignClientNoHystrixClientFallback.class)
public interface UserFeignClientNoHystrixClient {
	@GetMapping("/user/{id}")
	String getById(@PathVariable("id") long id);
}
