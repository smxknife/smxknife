package com.smxknife.energy.services.alarm.infras.dao;

import com.smxknife.energy.services.alarm.core.dao.AlarmRuleDao;
import com.smxknife.energy.services.alarm.infras.converter.AlarmRuleConverter;
import com.smxknife.energy.services.alarm.infras.entity.AlarmRuleMeta;
import com.smxknife.energy.services.alarm.infras.entity.AlarmRuleMetaRepository;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Service
public class AlarmRuleMySqlDao implements AlarmRuleDao {

	@Autowired
	private AlarmRuleMetaRepository repository;
	@Autowired
	private AlarmRuleConverter converter;

	@Override
	public List<AlarmRule> findAll() {
		return converter.fromMetas(repository.findAll());
	}

	@Override
	public void save(AlarmRule rule) {
		if (StringUtils.isEmpty(rule.getUid())) {
			throw new IllegalArgumentException("alarm rule uid is empty");
		}
		repository.findByUId(rule.getUid()).ifPresent(meta -> {
			throw new RuntimeException("alarm rule with uid is alreay exist");
		});

		repository.save(converter.toMeta(rule));
	}

	@Override
	public void update(AlarmRule rule) {
		if (StringUtils.isEmpty(rule.getUid())) {
			throw new IllegalArgumentException("alarm rule uid is empty");
		}

		repository.findByUId(rule.getUid()).ifPresent(meta -> {
			final AlarmRuleMeta ruleMeta = converter.toMeta(rule);
			ruleMeta.setId(meta.getId());
			repository.save(ruleMeta);
		});
	}

	@Override
	public void deleteByUid(String uid) {
		if (StringUtils.isEmpty(uid)) {
			throw new IllegalArgumentException("alarm rule uid is empty");
		}

		repository.findByUId(uid).ifPresent(meta -> repository.delete(meta));
	}
}
