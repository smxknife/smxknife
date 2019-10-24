package org.smxknife.axis.server.services;

import org.smxknife.axis.server.model.EncryptPwd;
import org.smxknife.axis.server.model.Password;

/**
 * @author smxknife
 * 2019/10/18
 */
public interface PasswordService {
	Password password(EncryptPwd pwd);
}
