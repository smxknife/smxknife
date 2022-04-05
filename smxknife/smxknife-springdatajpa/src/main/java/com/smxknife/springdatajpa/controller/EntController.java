package com.smxknife.springdatajpa.controller;

import com.smxknife.springdatajpa.model.ent.Enterprise;
import com.smxknife.springdatajpa.model.ent.Project;
import com.smxknife.springdatajpa.model.ent.WorkShop;
import com.smxknife.springdatajpa.repository.EnterpriseRepository;
import com.smxknife.springdatajpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2020/11/19
 */
@RestController
@RequestMapping("/ent")
public class EntController {

	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping
	public String insert() {
		Enterprise enterprise = new Enterprise();
		enterprise.setCreditCode("111111111111111111");
		enterprise.setManager("测试");
		enterprise.setName("测试企业");

		List<Project> entItems = new ArrayList<>();
		Project project1 = new Project();
		project1.setCode("prj-01");
		project1.setName("项目01");
		project1.setEnterprise(enterprise);
		project1.setManager("test1");
		entItems.add(project1);

		Project project2 = new Project();
		project2.setCode("prj-02");
		project2.setName("项目02");
		project2.setEnterprise(enterprise);
		project2.setManager("test2");
		entItems.add(project2);

		WorkShop workShop1 = new WorkShop();
		workShop1.setCode("wk-01");
		workShop1.setName("车间1");
		workShop1.setManager("车间负责人1");
		workShop1.setProject(project1);

		project1.setWorkShops(Arrays.asList(workShop1));

		enterprise.setProjects(entItems);

		enterpriseRepository.save(enterprise);

		return "xxx";
	}
}
