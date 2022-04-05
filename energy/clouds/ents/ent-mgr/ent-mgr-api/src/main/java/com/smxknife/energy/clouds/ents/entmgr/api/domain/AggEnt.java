package com.smxknife.energy.clouds.ents.entmgr.api.domain;

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
public class AggEnt {
	private String entName;
	private String entCode;

	private String account;
	private String username;
	private String mail;
	private String tel;

	private String industryCode;
	private String industryName;
	private String domainCode;
	private String domainName;
	private String regionCode;
	private String regionName;

}
