package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.entity.Account;
import com.smxknife.cloud.netflix.repository.AccountRepository;
import com.smxknife.cloud.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/31
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void debit(String userId, long money) {
		Account account = repository.findByUserId(userId);
		if (Objects.isNull(account)) {
			throw new IllegalArgumentException("Account with userId " + userId + " is not exist!");
		}
		final long accountMoney = account.getMoney();
		if (Double.compare(accountMoney, money) < 0) {
			throw new IllegalArgumentException("Account with userId " + userId + " money is not enough!");
		}

		account.setMoney(accountMoney - money);
		repository.save(account);
	}
}
