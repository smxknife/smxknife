package com.smxknife.drools.demo02.service;

import java.util.Map;

/**
 * @author smxknife
 * 2020/6/15
 */
public interface PromoteEdieService {
	void ediePromoteMap(String money, String ruleName);

	Map<String, Object> toShopping(String money);
}
