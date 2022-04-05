package com.smxknife.energy.clouds.ents.entmgr.web;

import com.smxknife.energy.clouds.ents.entmgr.api.domain.AggEnt;
import com.smxknife.energy.clouds.ents.entmgr.api.service.EntApi;
import com.smxknife.energy.clouds.ents.entmgr.service.EntMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@RestController
public class EntMgrController implements EntApi {

	@Autowired
	private EntMgrService entMgrService;

	@Override
	public List<AggEnt> list() {
		return entMgrService.list();
	}

	@Override
	public String create(AggEnt ent) {
		return entMgrService.create(ent);
	}
}
