package com.smxknife.energy.clouds.ents.entmgr.service;

import com.smxknife.energy.clouds.ents.entmgr.api.domain.AggEnt;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
public interface EntMgrService {
	List<AggEnt> list();
	String create(AggEnt ent);
}
