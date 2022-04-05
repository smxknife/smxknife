package com.smxknife.cloud.netflix.loadbalancer;

import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/27
 */
@Component
public class RibbonRequestThreadLocal {

	private ThreadLocal threadLocal = new ThreadLocal();

	public <T> void set(T obj) {
		threadLocal.set(obj);
	}

	public <T> T get() {
		return (T)threadLocal.get();
	}
}
