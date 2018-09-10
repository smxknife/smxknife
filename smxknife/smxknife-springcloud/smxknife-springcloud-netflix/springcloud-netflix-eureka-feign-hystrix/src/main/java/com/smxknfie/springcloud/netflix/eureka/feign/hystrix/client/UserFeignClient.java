package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client;

import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author smxknife
 * 2018/9/8
 */
@FeignClient(value = "user-service", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	String getUser();

	@RequestMapping(value = "/user/post", produces = "application/json")
	String postUser(String user);
}

