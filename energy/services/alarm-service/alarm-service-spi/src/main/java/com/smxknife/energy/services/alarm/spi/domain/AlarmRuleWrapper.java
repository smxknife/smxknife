package com.smxknife.energy.services.alarm.spi.domain;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
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
public class AlarmRuleWrapper {
	private AlarmRule rule;
	private String operation;
}
