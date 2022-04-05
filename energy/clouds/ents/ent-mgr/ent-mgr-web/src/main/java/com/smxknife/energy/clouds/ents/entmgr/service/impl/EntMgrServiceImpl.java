package com.smxknife.energy.clouds.ents.entmgr.service.impl;

import com.smxknife.energy.clouds.ents.entmgr.api.domain.AggEnt;
import com.smxknife.energy.clouds.ents.entmgr.entity.EntUserMapping;
import com.smxknife.energy.clouds.ents.entmgr.repository.EntUserMappingRepository;
import com.smxknife.energy.clouds.ents.entmgr.service.EntMgrService;
import com.smxknife.energy.services.enterprise.spi.domain.Enterprise;
import com.smxknife.energy.services.enterprise.spi.service.EnterpriseService;
import com.smxknife.energy.services.user.spi.domain.User;
import com.smxknife.energy.services.user.spi.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/19
 */
@Service
public class EntMgrServiceImpl implements EntMgrService {

	@DubboReference(version = "v1")
	private EnterpriseService enterpriseService;

	@DubboReference(version = "v1")
	private UserService userService;

	@Autowired
	private EntUserMappingRepository repository;

	@Override
	public List<AggEnt> list() {
		final List<EntUserMapping> mappings = repository.findAll();
		List<String> entCodes = new ArrayList<>();
		List<String> userAccounts = new ArrayList<>();

		mappings.forEach(mapping -> {
			entCodes.add(mapping.getEntCode());
			userAccounts.add(mapping.getAccount());
		});

		final List<User> users = userService.getByAccountIn(userAccounts);
		final Map<String, List<User>> userGroupMap = users.stream().collect(Collectors.groupingBy(User::getAccount));
		final List<Enterprise> enterprises = enterpriseService.getByCodeIn(entCodes);
		final Map<String, List<Enterprise>> entGroupMap = enterprises.stream().collect(Collectors.groupingBy(Enterprise::getCode));

		return mappings.stream().map(mapping -> {
			final Enterprise enterprise = entGroupMap.get(mapping.getEntCode()).get(0);
			final User user = userGroupMap.get(mapping.getAccount()).get(0);
			return new AggEnt(enterprise.getName(), enterprise.getCode(),
					user.getAccount(), user.getUsername(), user.getMail(), user.getTel(),
					enterprise.getIndustryCode(), enterprise.getIndustryName(),
					enterprise.getDomainCode(), enterprise.getDomainName(),
					enterprise.getRegionCode(), enterprise.getRegionName()
					);
		}).collect(Collectors.toList());

	}

	@Override
	public String create(AggEnt ent) {

		// TODO 这里需要增加分布式事务和分布式锁
		/**
		 * 分布式锁：为了保证同时只有一个人可以操作成功
		 * 分布式事务：保证该操作要么所有服务都成功，要么都失败
		 */
		final EntUserMapping entUserMapping = new EntUserMapping(null,
				ent.getEntName(), ent.getEntCode(), ent.getAccount(), ent.getUsername(), ent.getMail(), ent.getTel());

		final User user = new User(ent.getAccount(), ent.getUsername(), ent.getMail(), ent.getTel());
		final Enterprise enterprise = new Enterprise(ent.getEntCode(), ent.getEntName(),
				ent.getIndustryCode(), ent.getIndustryName(),
				ent.getDomainCode(), ent.getDomainName(),
				ent.getRegionCode(), ent.getRegionName()
		);

		repository.save(entUserMapping);
		userService.create(user);
		enterpriseService.create(enterprise);

		return "创建成功";
	}
}
