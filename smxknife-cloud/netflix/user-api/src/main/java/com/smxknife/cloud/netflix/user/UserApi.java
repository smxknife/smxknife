package com.smxknife.cloud.netflix.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author smxknife
 * 2021/4/30
 */
//@RequestMapping("/user")
public interface UserApi {

	@GetMapping("name")
	String getName();

	@PostMapping("getById")
	User getById(Long id);

}
