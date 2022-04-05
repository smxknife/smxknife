package com.smxknife.energy.services.alarm.infras.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/17
 */
@Repository
public interface AlarmRuleMetaRepository extends JpaRepository<AlarmRuleMeta, Long> {
	Optional<AlarmRuleMeta> findByUId(String uid);
}
