package com.smxknife.energy.services.alarm.core.dao;

import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmHandleRecordDao {
	List<AlarmHandleRecord> findByRecordUid(String recordUid);

	List<AlarmHandleRecord> findByHandler(String handler);
}
