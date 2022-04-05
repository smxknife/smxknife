package com.smxknife.energy.services.user.infras.dao;

import com.smxknife.energy.services.user.core.dao.UserDao;
import com.smxknife.energy.services.user.infras.converter.UserConverter;
import com.smxknife.energy.services.user.infras.repository.UserMetaRepository;
import com.smxknife.energy.services.user.spi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserMetaRepository userMetaRepository;
	@Autowired
	private UserConverter converter;

	@Override
	public void create(User user) {
		userMetaRepository.save(converter.toMeta(user));
	}

	@Override
	public List<User> getAll() {
		return converter.fromMetas(userMetaRepository.findAll());
	}

	@Override
	public List<User> getByAccountIn(List<String> accounts) {
		return converter.fromMetas(userMetaRepository.findByAccountIn(accounts));
	}
}
