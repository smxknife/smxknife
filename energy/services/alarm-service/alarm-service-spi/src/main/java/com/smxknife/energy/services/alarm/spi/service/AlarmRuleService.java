package com.smxknife.energy.services.alarm.spi.service;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmRuleService {

	List<AlarmRule> getAll();

	void createRule(AlarmRule rule);

	void edit(AlarmRule rule);

	void deleteByUid(String uid);
}
