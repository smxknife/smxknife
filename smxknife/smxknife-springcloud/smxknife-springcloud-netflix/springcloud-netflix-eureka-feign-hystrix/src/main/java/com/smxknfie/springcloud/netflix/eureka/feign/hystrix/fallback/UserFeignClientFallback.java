package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.fallback;

import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2018/9/8
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

	@Override
	public String getUser() {
		return "getUser fallback";
	}

	@Override
	public String postUser(String user) {
		return "postUser fallback " + user ;
	}
}
