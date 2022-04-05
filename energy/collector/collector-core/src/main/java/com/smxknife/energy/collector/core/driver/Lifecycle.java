package com.smxknife.energy.collector.core.driver;

/**
 * @author smxknife
 * 2021/5/12
 */
public interface Lifecycle {
	default void init() {};
	default void start() {};
	default void stop() {};
	default void terminate() {};
}
