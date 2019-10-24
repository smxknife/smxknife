package org.smxknife.axis.server.services.impl;

import org.smxknife.axis.server.model.EncryptPwd;
import org.smxknife.axis.server.model.Password;
import org.smxknife.axis.server.services.PasswordService;

/**
 * @author smxknife
 * 2019/10/18
 */
public class PasswordServiceImpl implements PasswordService {
	@Override
	public Password password(EncryptPwd pwd) {
		Password password = pwd.getPassword();
		return password;
	}
}
