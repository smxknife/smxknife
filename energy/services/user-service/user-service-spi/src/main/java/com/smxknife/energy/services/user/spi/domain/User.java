package com.smxknife.energy.services.user.spi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smxknife
 * 2021/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String account;
	private String username;
	private String mail;
	private String tel;
}
