package com.smxknife.energy.services.alarm.infras.entity;

import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Repository
public interface AlarmHandleRecordMetaRepository extends JpaRepository<AlarmHandleRecordMeta, Long> {
	List<AlarmHandleRecord> findByRecordUid(String recordUid);

	List<AlarmHandleRecord> findByHandler(String handler);

}
