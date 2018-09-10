package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.factory;

import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignWithHystrixFactoryClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2018/9/9
 */
@Component
@Log
public class UserFeignWithHystrixFactory implements FallbackFactory<UserFeignWithHystrixFactoryClient> {

	@Override
	public UserFeignWithHystrixFactoryClient create(Throwable throwable) {
		return new UserFeignWithHystrixFactoryClient() {
			@Override
			public String postUser(String user) {
				log.info(">> factory");
				return "factory fallback " + user;
			}
		};
	}
}
