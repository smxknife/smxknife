package com.smxknife.energy.services.enterprise.core.dao;

import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
public interface EnterpriseDao {
	void create(Enterprise enterprise);
	List<Enterprise> getAll();

	List<Enterprise> getByCodeIn(List<String> entCodes);
}
