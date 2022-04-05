package com.smxknife.energy.services.alarm.core.service;

import com.smxknife.energy.services.alarm.core.dao.AlarmRecordDao;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import com.smxknife.energy.services.alarm.spi.service.AlarmRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@DubboService(version = "v1")
public class AlarmRecordServiceImpl implements AlarmRecordService {

	@Autowired
	private AlarmRecordDao alarmRecordDao;

	@Override
	public List<AlarmRecord> getAll() {
		return alarmRecordDao.findAll();
	}

	@Override
	public List<AlarmRecord> getAllByReporter(String reporter) {
		return alarmRecordDao.findByReporter(reporter);
	}
}
