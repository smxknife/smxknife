package com.smxknife.mapstruct.demo02;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author smxknife
 * 2021/3/8
 */
@Mapper
public interface Mapper02 {
	Mapper02 INSTANCE = Mappers.getMapper(Mapper02.class);

	@Mappings({
			@Mapping(target = "name"),
			@Mapping(target = "entityId", expression = "java(new com.smxknife.mapstruct.demo02.EntityId(dto02.getId()))")
	})
	Entity02 toEntity(Dto02 dto02);
}
