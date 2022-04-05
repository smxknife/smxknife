package com.smxknife.cloud.netflix.user.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author smxknife
 * 2021/4/30
 */
//@FeignClient(name = "userApi", url = "http://user-provider/user") // 这种方式会报错
//@FeignClient(name = "userApi", url = "http://localhost:9000/user") // 这种方式是对的，但是这里相当于直连，没有负载均衡
//@FeignClient(name = "user-provider", path = "user")
public interface UserConsumerApi {

	@GetMapping("alive")
	String isAlive();
}
