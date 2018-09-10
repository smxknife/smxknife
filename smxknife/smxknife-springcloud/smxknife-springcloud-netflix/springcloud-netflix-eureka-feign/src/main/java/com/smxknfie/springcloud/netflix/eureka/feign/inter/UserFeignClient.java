package com.smxknfie.springcloud.netflix.eureka.feign.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author smxknife
 * 2018/9/7
 */
@FeignClient("user-service")
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	String getUser();

	@GetMapping("/user/{id}")
	String getById(@PathVariable("id") long id);

//	@RequestMapping(method = RequestMethod.POST, value = "/user/{id}", consumes = "application/json")
//	Store update(@PathVariable("storeId") Long storeId, Store store);

	@RequestMapping(value = "/user/post", produces = "application/json")
	String postUser(String user);
}
