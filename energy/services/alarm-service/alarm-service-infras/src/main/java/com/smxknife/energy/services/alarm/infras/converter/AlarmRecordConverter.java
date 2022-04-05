package com.smxknife.energy.services.alarm.infras.converter;

import com.smxknife.energy.services.alarm.infras.entity.AlarmRecordMeta;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Mapper(componentModel = "spring")
public interface AlarmRecordConverter {

	@Mappings({})
	AlarmRecord fromMeta(AlarmRecordMeta meta);

	@Mappings({})
	List<AlarmRecord> fromMetas(List<AlarmRecordMeta> metas);

	@Mappings({})
	AlarmRecordMeta toMeta(AlarmRecord alarmRecord);

	@Mappings({})
	List<AlarmRecordMeta> toMetas(List<AlarmRecord> alarmRecords);
}
