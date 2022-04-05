package com.smxknife.energy.services.enterprise.spi.service;

import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
public interface EnterpriseService {

	void create(Enterprise enterprise);

	List<Enterprise> getAll();

	List<Enterprise> getByCodeIn(List<String> entCodes);
}
