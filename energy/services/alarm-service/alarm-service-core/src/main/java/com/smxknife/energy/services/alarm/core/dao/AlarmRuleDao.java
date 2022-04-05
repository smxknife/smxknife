package com.smxknife.energy.services.alarm.core.dao;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmRuleDao {
	List<AlarmRule> findAll();

	void save(AlarmRule rule);

	void update(AlarmRule rule);

	void deleteByUid(String uid);

}
