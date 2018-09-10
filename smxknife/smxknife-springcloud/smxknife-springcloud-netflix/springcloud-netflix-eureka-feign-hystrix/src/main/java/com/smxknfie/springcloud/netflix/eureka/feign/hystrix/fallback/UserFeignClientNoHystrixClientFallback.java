package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.fallback;

import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignClientNoHystrixClient;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2018/9/9
 */
@Component
public class UserFeignClientNoHystrixClientFallback implements UserFeignClientNoHystrixClient {
	@Override
	public String getById(long id) {
		return "getById fallback [ " + id + " ]";
	}
}
