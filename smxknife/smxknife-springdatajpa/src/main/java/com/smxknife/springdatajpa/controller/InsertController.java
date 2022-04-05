package com.smxknife.springdatajpa.controller;

import com.smxknife.springdatajpa.entity.AccountElement;
import com.smxknife.springdatajpa.repository.AccountElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/11/16
 */
@RestController
@RequestMapping("/insert")
public class InsertController {

	@Autowired
	AccountElementRepository repository;

	@RequestMapping
	public String insert() {
		for (int i = 0; i < 100; i++) {
			List<AccountElement> accountElementList = new ArrayList<>();
			for (int j = 0; j < 2400; j++) {
				AccountElement accountElement = new AccountElement();
				accountElement.setCode(String.format("1002.01.002.%ss%s", i, j));
				accountElement.setName(String.format("名称%ss%s", i, j));
				accountElement.setFullname(String.format("银行存款_002_%ss%s", i, j));
				accountElement.setParent("1002.002");
				accountElement.setOriginalCurrency("CNY");
				accountElement.setLevelNo(4);
				accountElement.setLeaf(false);
				accountElementList.add(accountElement);
			}
			repository.saveAll(accountElementList);
			System.out.println("插入第 " + i + " 批");
		}
		return "finish";

	}
}
