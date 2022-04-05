package com.smxknife.energy.services.alarm.core.dao;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmRecordDao {
	List<AlarmRecord> findAll();

	List<AlarmRecord> findByReporter(String reporter);

}
