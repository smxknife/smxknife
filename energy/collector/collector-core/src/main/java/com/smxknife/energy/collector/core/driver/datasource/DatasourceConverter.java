package com.smxknife.energy.collector.core.driver.datasource;

import com.smxknife.energy.collector.core.entity.DatasourceMeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DatasourceConverter {
	@Mappings({
			@Mapping(target = "type", source = "type", ignore = false)
	})
	Datasource fromMeta(DatasourceMeta meta);
}