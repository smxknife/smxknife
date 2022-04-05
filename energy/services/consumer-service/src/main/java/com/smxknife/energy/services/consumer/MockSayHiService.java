package com.smxknife.energy.services.consumer;

import com.smxknife.energy.services.spi.SayHiService;

/**
 * @author smxknife
 * 2021/5/26
 */
public class MockSayHiService implements SayHiService {
	@Override
	public String sayHi() {
		return "系统繁忙";
	}

	@Override
	public String sayHiTimeout() {
		return "系统繁忙";
	}

	@Override
	public String sayHiFailover() {
		return "系统繁忙";
	}

	@Override
	public String sayHiFailfast() {
		return "系统繁忙";
	}

	@Override
	public String sayHiFailSafe() {
		return "系统繁忙";
	}

	@Override
	public String sayFailback() {
		return "系统繁忙";
	}

	@Override
	public String sayHiForking() {
		return "系统繁忙";
	}

	@Override
	public String sayHiBroadcast() {
		return "系统繁忙";
	}
}
