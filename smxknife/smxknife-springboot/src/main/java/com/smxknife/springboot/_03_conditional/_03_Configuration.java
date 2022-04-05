package com.smxknife.springboot._03_conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2021/2/20
 */
@Configuration
@ConditionalOnProperty({"ha.test1", "ha.test2"})
@ConditionalOnClass(name = "redis.clients.jedis.Jedis")
public class _03_Configuration {

	public _03_Configuration() {
		System.out.println("_03_Configuration constructor");
	}
}
