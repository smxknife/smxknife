package com.smxknife.energy.services.alarm.spi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRule {

	private String description;
	private String rule;
	private String uid;
	private String type;
}
