package com.smxknife.energy.services.consumer;

import com.smxknife.energy.services.spi.SayHiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.cluster.support.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/24
 */
@RestController
@RequestMapping("/say")
public class SayHiController {

	// 这里默认指定版本号
//	@DubboReference(version = "1.0")
	// 这里指定轮询，这里的timeout会覆盖provider
//	@DubboReference(version = "1.0", timeout = 10000, loadbalance = RoundRobinLoadBalance.NAME)
	// 如果没有指定，使用provider的timeout
//	@DubboReference(version = "1.0", loadbalance = RoundRobinLoadBalance.NAME)
	// 指定集群容错
	/**
	 * FailoverCluster: 失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟
	 */
	@DubboReference(version = "1.0", cluster = FailoverCluster.NAME)
	SayHiService sayHiService;

	@GetMapping
	public String say() {
		return sayHiService.sayHi();
	}

	@GetMapping("to")
	public String sayTo() {
		return sayHiService.sayHiTimeout();
	}

	@DubboReference(version = "1.0", cluster = FailoverCluster.NAME)
	SayHiService failoverSayHiService;
	@GetMapping("fo")
	public String sayFailover() {
		return failoverSayHiService.sayHiFailover();
	}

	@DubboReference(version = "1.0", cluster = FailfastCluster.NAME)
	SayHiService failfastSayHiService;
	@GetMapping("ff")
	public String sayFailfast() {
		return failfastSayHiService.sayHiFailfast();
	}

	@DubboReference(version = "1.0", cluster = FailsafeCluster.NAME)
	SayHiService failSafeSayHiService;
	@GetMapping("fs")
	public String sayFailSafe() {
		return failSafeSayHiService.sayHiFailSafe();
	}

	@DubboReference(version = "1.0", cluster = FailbackCluster.NAME)
	SayHiService failBackSayHiService;
	@GetMapping("fb")
	public String sayFailback() {
		return failBackSayHiService.sayFailback();
	}

	@DubboReference(version = "1.0", cluster = ForkingCluster.NAME)
	SayHiService forkingSayHiService;
	@GetMapping("fc")
	public String sayForking() {
		return forkingSayHiService.sayHiForking();
	}

	@DubboReference(version = "1.0", cluster = "broadcast")
	SayHiService broadcastSayHiService;
	@GetMapping("b")
	public String sayBroadcast() {
		return broadcastSayHiService.sayHiBroadcast();
	}

	@DubboReference(version = "1.0", timeout = 5000, mock = "com.smxknife.energy.services.consumer.MockSayHiService")
	SayHiService callMockSayHiService;
	@GetMapping("mock")
	public String mockSay() {
		return callMockSayHiService.sayHi();
	}

}
