package com.smxknife.springboot.ebean.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author smxknife
 * 2020/11/16
 */
@Getter
@Setter
@Entity(name = "ta_accountelement")
public class AccountElement {

	@Id
	private Long id;
	private String code;
	private String name;
	private String fullname;
	private String parent;
	private String originalCurrency;
	private int levelNo;
	private boolean isLeaf;
	private String elementCode;
	private String indexCondition;
	private String planCode;
}
