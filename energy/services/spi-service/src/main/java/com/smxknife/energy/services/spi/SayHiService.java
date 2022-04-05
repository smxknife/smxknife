package com.smxknife.energy.services.spi;

/**
 * @author smxknife
 * 2021/5/24
 */
public interface SayHiService {
	String sayHi();

	String sayHiTimeout();

	String sayHiFailover();

	String sayHiFailfast();

	String sayHiFailSafe();

	String sayFailback();

	String sayHiForking();

	String sayHiBroadcast();
}
