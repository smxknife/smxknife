package com.smxknife.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author smxknife
 * 2020/11/16
 */
@Getter
@Setter
@Entity
@Table(name = "ta_accountelement")
public class AccountElement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
