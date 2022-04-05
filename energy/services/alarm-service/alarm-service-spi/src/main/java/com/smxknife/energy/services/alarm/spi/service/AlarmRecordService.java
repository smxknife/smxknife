package com.smxknife.energy.services.alarm.spi.service;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface AlarmRecordService {

	List<AlarmRecord> getAll();

	List<AlarmRecord> getAllByReporter(String reporter);

}
