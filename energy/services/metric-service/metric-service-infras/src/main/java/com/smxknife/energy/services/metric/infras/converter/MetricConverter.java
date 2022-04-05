package com.smxknife.energy.services.metric.infras.converter;

import com.smxknife.energy.services.metric.infras.entity.MetricMeta;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * @author smxknife
 * 2021/5/17
 */
@Mapper(componentModel = "spring")
public interface MetricConverter {

	@Mappings({})
	MetricMeta toMeta(Metric metric);

	@Mappings({})
	Metric fromMeta(MetricMeta meta);
}
