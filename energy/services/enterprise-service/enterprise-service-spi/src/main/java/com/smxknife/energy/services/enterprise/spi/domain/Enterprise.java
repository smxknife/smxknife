package com.smxknife.energy.services.enterprise.spi.domain;

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
public class Enterprise {

	private String code;
	private String name;
	private String industryCode;
	private String industryName;
	private String domainCode;
	private String domainName;
	private String regionCode;
	private String regionName;

}
