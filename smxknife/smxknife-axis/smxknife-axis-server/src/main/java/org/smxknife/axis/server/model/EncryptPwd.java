package org.smxknife.axis.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author smxknife
 * 2019/10/18
 */
@Getter
@Setter
@ToString
public class EncryptPwd {
	private Password password;
	private String salt;
}
