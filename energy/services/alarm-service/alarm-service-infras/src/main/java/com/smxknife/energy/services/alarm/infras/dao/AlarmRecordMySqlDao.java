package com.smxknife.energy.services.alarm.infras.dao;

import com.smxknife.energy.services.alarm.core.dao.AlarmRecordDao;
import com.smxknife.energy.services.alarm.infras.converter.AlarmRecordConverter;
import com.smxknife.energy.services.alarm.infras.entity.AlarmRecordMetaRepository;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Service
public class AlarmRecordMySqlDao implements AlarmRecordDao {

	@Autowired
	private AlarmRecordMetaRepository repository;
	@Autowired
	private AlarmRecordConverter converter;

	@Override
	public List<AlarmRecord> findAll() {
		return converter.fromMetas(repository.findAll());
	}

	@Override
	public List<AlarmRecord> findByReporter(String reporter) {
		return converter.fromMetas(repository.findByReporter(reporter));
	}
}
