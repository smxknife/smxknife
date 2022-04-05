package com.smxknife.energy.services.user.infras.converter;

import com.smxknife.energy.services.user.infras.entity.UserMeta;
import com.smxknife.energy.services.user.spi.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({})
	UserMeta toMeta(User user);

	@Mappings({})
	User fromMeta(UserMeta meta);

	@Mappings({})
	List<User> fromMetas(List<UserMeta> metas);
}
