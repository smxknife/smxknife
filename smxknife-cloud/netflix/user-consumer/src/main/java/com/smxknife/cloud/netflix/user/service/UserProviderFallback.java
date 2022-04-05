package com.smxknife.cloud.netflix.user.service;

import com.smxknife.cloud.netflix.user.User;
import org.springframework.stereotype.Component;

@Component("FALLBACK")
public class UserProviderFallback implements FeignHystrixUserApi {

	@Override
	public String getName() {
		return "降级了。。。";
	}

	@Override
	public User getById(Long id) {
		System.out.println("getById 降级了");
		return null;
	}
}
