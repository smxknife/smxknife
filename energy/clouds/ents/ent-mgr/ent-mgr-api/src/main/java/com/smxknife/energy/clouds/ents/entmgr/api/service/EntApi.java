package com.smxknife.energy.clouds.ents.entmgr.api.service;

import com.smxknife.energy.clouds.ents.entmgr.api.domain.AggEnt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
public interface EntApi {

	@GetMapping("list")
	List<AggEnt> list();

	@PostMapping("create")
	String create(AggEnt ent);
}
