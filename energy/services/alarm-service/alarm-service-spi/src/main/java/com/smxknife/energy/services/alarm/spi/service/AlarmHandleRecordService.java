package com.smxknife.energy.services.alarm.spi.service;

import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmHandleRecordService {

	List<AlarmHandleRecord> getAllByRecordUid(String recordUid);

	List<AlarmHandleRecord> getAllByHandler(String handler);

}
