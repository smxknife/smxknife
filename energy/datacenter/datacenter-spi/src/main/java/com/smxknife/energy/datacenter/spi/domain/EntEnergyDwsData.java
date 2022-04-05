package com.smxknife.energy.datacenter.spi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntEnergyDwsData {
	// entInfo
	private String entCode;

	// energyInfo
	private String energyCode;
	private String dataCode;
	private Double dataValue;
	private LocalDateTime dateTime;

	// industryInfo
	private String industryCode;
	private String domainCode;

	// regionInfo
	private String regionCode;


}
