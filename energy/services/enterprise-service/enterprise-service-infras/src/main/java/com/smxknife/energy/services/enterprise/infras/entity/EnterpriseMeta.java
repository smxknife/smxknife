package com.smxknife.energy.services.enterprise.infras.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/19
 */
@Data
@Entity
@Table
public class EnterpriseMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
	private String name;
	private String industryCode;
	private String industryName;
	private String domainCode;
	private String domainName;
	private String regionCode;
	private String regionName;
}
