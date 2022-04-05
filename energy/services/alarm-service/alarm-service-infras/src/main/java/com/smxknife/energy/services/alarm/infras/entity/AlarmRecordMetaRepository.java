package com.smxknife.energy.services.alarm.infras.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Repository
public interface AlarmRecordMetaRepository extends JpaRepository<AlarmRecordMeta, Long> {
	List<AlarmRecordMeta> findByReporter(String reporter);
}
