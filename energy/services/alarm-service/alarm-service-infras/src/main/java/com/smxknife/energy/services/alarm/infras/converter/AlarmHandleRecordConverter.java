package com.smxknife.energy.services.alarm.infras.converter;

import com.smxknife.energy.services.alarm.infras.entity.AlarmHandleRecordMeta;
import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@Mapper(componentModel = "spring")
public interface AlarmHandleRecordConverter {

	@Mappings({})
	AlarmHandleRecord fromMeta(AlarmHandleRecordMeta meta);

	@Mappings({})
	AlarmHandleRecordMeta toMeta(AlarmHandleRecord alarmHandleRecord);

	@Mappings({})
	List<AlarmHandleRecordMeta> toMetas(List<AlarmHandleRecord> alarmHandleRecords);
}
