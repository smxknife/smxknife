package com.smxknife.energy.services.alarm.core.service;

import com.smxknife.energy.services.alarm.core.dao.AlarmRuleDao;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
import com.smxknife.energy.services.alarm.spi.service.AlarmRuleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@DubboService(version = "v1")
public class AlarmRuleServiceImpl implements AlarmRuleService {

	@Autowired
	private AlarmRuleDao alarmRuleDao;

	@Override
	public List<AlarmRule> getAll() {
		return alarmRuleDao.findAll();
	}

	@Override
	public void createRule(AlarmRule rule) {
		alarmRuleDao.save(rule);
	}

	@Override
	public void edit(AlarmRule rule) {
		alarmRuleDao.update(rule);
	}

	@Override
	public void deleteByUid(String uid) {
		alarmRuleDao.deleteByUid(uid);
	}
}
