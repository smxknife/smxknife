package com.smxknife.energy.services.metric.infras.converter;

import com.smxknife.energy.services.metric.infras.entity.DatapointData;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * @author smxknife
 * 2021/5/17
 */
@Mapper(componentModel = "spring")
public interface DatapointConverter {

	@Mappings({
//			@Mapping(target = "id", ignore = true)
	})
	DatapointData toData(Datapoint datapoint);

	@Mappings({})
	Datapoint fromData(DatapointData data);
}
