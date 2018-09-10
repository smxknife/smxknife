package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client;

import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.factory.UserFeignWithHystrixFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smxknife
 * 2018/9/8
 */
@FeignClient(value = "user-service", fallbackFactory = UserFeignWithHystrixFactory.class)
public interface UserFeignWithHystrixFactoryClient {

//	@RequestMapping(method = RequestMethod.GET, value = "/user")
//	String getUser();

	@RequestMapping(value = "/user/post", produces = "application/json")
	String postUser(String user);
}

