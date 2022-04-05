package com.smxknife.cloud.netflix.web.controller;

import com.smxknife.cloud.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/31
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("debit")
	public String debit(String userId, long money) {
		accountService.debit(userId, money);
		return String.format("userId: %s, money: %s 账户借出成功", userId, money);
	}
}
