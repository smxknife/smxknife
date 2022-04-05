package com.smxknife.energy.services.provider;

import com.smxknife.energy.services.spi.SayHiService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/24
 */
@DubboService(version = "1.0", retries = 3, timeout = 5000, interfaceClass = SayHiService.class)
public class SayHiServiceImpl implements SayHiService {
	
	@Value("${server.port}")
	int port;

	@Override
	public String sayHi() {
		return "hi, I am " + port;
	}

	@Override
	public String sayHiTimeout() {
		try {
			System.out.println(port + " : 有请求来了。。。");
			TimeUnit.SECONDS.sleep(11);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hi, I am " + port;
	}

	@Override
	public String sayHiFailover() {
		System.out.println(port + " : Failover 有请求来了。。。");
		throw new RuntimeException("Failover");
	}

	@Override
	public String sayHiFailfast() {
		System.out.println(port + " : Failfast 有请求来了。。。");
		throw new RuntimeException("Failfast");
	}

	@Override
	public String sayHiFailSafe() {
		System.out.println(port + " : Failsafe 有请求来了。。。");
		throw new RuntimeException("Failsafe");
	}

	@Override
	public String sayFailback() {
		System.out.println(port + " : Failback 有请求来了。。。");
		throw new RuntimeException("Failback");
	}

	@Override
	public String sayHiForking() {
		System.out.println(port + " : Forking 有请求来了。。。");
		throw new RuntimeException("Forking");
	}

	@Override
	public String sayHiBroadcast() {
		System.out.println(port + " : Broadcast 有请求来了。。。");
		throw new RuntimeException("Broadcast");
	}
}
