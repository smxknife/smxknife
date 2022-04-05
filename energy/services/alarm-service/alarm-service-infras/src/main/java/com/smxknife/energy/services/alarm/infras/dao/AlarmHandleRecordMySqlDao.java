package com.smxknife.energy.services.alarm.infras.dao;

import com.smxknife.energy.services.alarm.core.dao.AlarmHandleRecordDao;
import com.smxknife.energy.services.alarm.infras.converter.AlarmHandleRecordConverter;
import com.smxknife.energy.services.alarm.infras.entity.AlarmHandleRecordMetaRepository;
import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Service
public class AlarmHandleRecordMySqlDao implements AlarmHandleRecordDao {

	@Autowired
	private AlarmHandleRecordMetaRepository repository;
	@Autowired
	private AlarmHandleRecordConverter converter;

	@Override
	public List<AlarmHandleRecord> findByRecordUid(String recordUid) {
		return repository.findByRecordUid(recordUid);
	}

	@Override
	public List<AlarmHandleRecord> findByHandler(String handler) {
		return repository.findByHandler(handler);
	}
}
