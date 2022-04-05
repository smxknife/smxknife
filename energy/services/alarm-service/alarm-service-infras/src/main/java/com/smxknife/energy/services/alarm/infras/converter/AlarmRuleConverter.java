package com.smxknife.energy.services.alarm.infras.converter;

import com.smxknife.energy.services.alarm.infras.entity.AlarmRuleMeta;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Mapper(componentModel = "spring")
public interface AlarmRuleConverter {

	@Mappings({})
	AlarmRule fromMeta(AlarmRuleMeta meta);

	@Mappings({})
	List<AlarmRule> fromMetas(List<AlarmRuleMeta> metas);

	@Mappings({})
	AlarmRuleMeta toMeta(AlarmRule alarmRule);
}
