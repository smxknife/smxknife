package com.smxknife.cloud.netflix.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author smxknife
 * 2021/5/27
 */
@Deprecated
@Slf4j
//@Component
public class GrayLoadbalancerRule extends AbstractLoadBalancerRule {

	@Autowired
	private RibbonRequestThreadLocal ribbonRequestThreadLocal;

	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {

	}

	@Override
	public Server choose(Object o) {
		return choose(getLoadBalancer(), o);
	}

	/**
	 * 这里通过拦截器设置请求参数到ThreadLocal，这里获取
	 * @param loadBalancer
	 * @param o
	 * @return
	 */
	private Server choose(ILoadBalancer loadBalancer, Object o) {
		System.out.println("灰度，Rule");
		final String version = ribbonRequestThreadLocal.<String>get();

		// 这种方式其实是错误的，孙然实现了灰度发布，但是却丢失了负载均衡，永远只会往第一个上面发
		// 所以，弃用
		return loadBalancer.getReachableServers().stream().filter(server -> {

			if (server instanceof DiscoveryEnabledServer) {
				log.info("server is DiscoveryEnabledServer..............");
				final DiscoveryEnabledServer discoveryEnabledServer = (DiscoveryEnabledServer) server;
				final String instanceVersion = discoveryEnabledServer.getInstanceInfo().getMetadata().get("version");

				if (instanceVersion.equals(version)) {
					return true;
				} else {
					return false;
				}
			}
			return true;
		}).unordered().findAny().orElseThrow(() -> new RuntimeException("no server"));
	}
}
