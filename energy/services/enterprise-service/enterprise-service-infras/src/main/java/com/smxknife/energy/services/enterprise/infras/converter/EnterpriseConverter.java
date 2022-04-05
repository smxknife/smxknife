package com.smxknife.energy.services.enterprise.infras.converter;

import com.smxknife.energy.services.enterprise.infras.entity.EnterpriseMeta;
import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Mapper(componentModel = "spring")
public interface EnterpriseConverter {

	@Mappings({})
	EnterpriseMeta toMeta(Enterprise enterprise);

	@Mappings({})
	List<Enterprise> fromMetas(List<EnterpriseMeta> meta);
}
