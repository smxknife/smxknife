package com.smxknife.springboot._03_conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/2/20
 */
@Component
@ConditionalOnProperty({"ha.test1", "ha.test2"})
@ConditionalOnClass(name = "redis.clients.jedis.Jedis")
public class _03_Component {
	public _03_Component() {
		System.out.println("_03_Component constructor");
	}
}
