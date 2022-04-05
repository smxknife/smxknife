package com.smxknife.cloud.netflix.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/27
 */
@Slf4j
@Component
public class GrayLoadbalancerRule2 extends AbstractLoadBalancerRule {

	@Autowired
	private RibbonRequestThreadLocal ribbonRequestThreadLocal;

	private RoundRobinRule roundRobinRule = new RoundRobinRule();

	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {
		roundRobinRule.initWithNiwsConfig(iClientConfig);
	}

	@Override
	public Server choose(Object o) {
		System.out.println("------ " + o);
		return choose(getLoadBalancer(), o).orElseGet(() -> {
			return roundRobinRule.choose(o);
		});
	}

	/**
	 * 这里通过拦截器设置请求参数到ThreadLocal，这里获取
	 * @param loadBalancer
	 * @param o
	 * @return
	 */
	private Optional<Server> choose(ILoadBalancer loadBalancer, Object o) {
		System.out.println("灰度，Rule");
		final String version = ribbonRequestThreadLocal.<String>get();

		return loadBalancer.getReachableServers().stream().filter(server -> {

			if (server instanceof DiscoveryEnabledServer) {
				log.info("server is DiscoveryEnabledServer..............");
				final DiscoveryEnabledServer discoveryEnabledServer = (DiscoveryEnabledServer) server;
				final String instanceVersion = discoveryEnabledServer.getInstanceInfo().getMetadata().get("version");

				if (instanceVersion.equals(version)) {
					return true;
				}
			}
			return false;
		}).unordered().findAny();
	}
}
