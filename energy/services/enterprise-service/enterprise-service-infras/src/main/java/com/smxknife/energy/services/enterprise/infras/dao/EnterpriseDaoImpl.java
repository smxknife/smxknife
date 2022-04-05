package com.smxknife.energy.services.enterprise.infras.dao;

import com.smxknife.energy.services.enterprise.core.dao.EnterpriseDao;
import com.smxknife.energy.services.enterprise.infras.converter.EnterpriseConverter;
import com.smxknife.energy.services.enterprise.infras.repository.EnterpriseMetaRepository;
import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@Service
public class EnterpriseDaoImpl implements EnterpriseDao {

	@Autowired
	private EnterpriseMetaRepository metaRepository;
	@Autowired
	private EnterpriseConverter converter;

	@Override
	public void create(Enterprise enterprise) {
		metaRepository.save(converter.toMeta(enterprise));
	}

	@Override
	public List<Enterprise> getAll() {
		return converter.fromMetas(metaRepository.findAll());
	}

	@Override
	public List<Enterprise> getByCodeIn(List<String> entCodes) {
		return converter.fromMetas(metaRepository.findByCodeIn(entCodes));
	}
}
