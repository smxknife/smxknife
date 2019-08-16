package com.smxknife.springboot.v2.jpa.transaction.service;

/**
 * @author smxknife
 * 2019-08-09
 */
public interface ZooInitService {

	 void normal();

	 void nonTranException() throws Exception;

	 void tranException() throws Exception;

	 void annWithTranException() throws Exception;
}
