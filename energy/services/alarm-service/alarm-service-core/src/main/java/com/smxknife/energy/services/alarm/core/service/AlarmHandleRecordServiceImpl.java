package com.smxknife.energy.services.alarm.core.service;

import com.smxknife.energy.services.alarm.core.dao.AlarmHandleRecordDao;
import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import com.smxknife.energy.services.alarm.spi.service.AlarmHandleRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@DubboService(version = "v1")
public class AlarmHandleRecordServiceImpl implements AlarmHandleRecordService {

	@Autowired
	private AlarmHandleRecordDao alarmHandleRecordDao;

	@Override
	public List<AlarmHandleRecord> getAllByRecordUid(String recordUid) {
		return alarmHandleRecordDao.findByRecordUid(recordUid);
	}

	@Override
	public List<AlarmHandleRecord> getAllByHandler(String handler) {
		return alarmHandleRecordDao.findByHandler(handler);
	}
}
