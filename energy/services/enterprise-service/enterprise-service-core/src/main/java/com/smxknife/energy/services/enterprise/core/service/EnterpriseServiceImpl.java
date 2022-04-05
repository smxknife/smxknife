package com.smxknife.energy.services.enterprise.core.service;

import com.smxknife.energy.services.enterprise.core.dao.EnterpriseDao;
import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;
import com.smxknife.energy.services.enterprise.spi.service.EnterpriseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@DubboService(version = "v1", interfaceClass = EnterpriseService.class)
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Override
	public void create(Enterprise enterprise) {
		enterpriseDao.create(enterprise);
	}

	@Override
	public List<Enterprise> getAll() {
		return enterpriseDao.getAll();
	}

	@Override
	public List<Enterprise> getByCodeIn(List<String> entCodes) {
		return enterpriseDao.getByCodeIn(entCodes);
	}
}
