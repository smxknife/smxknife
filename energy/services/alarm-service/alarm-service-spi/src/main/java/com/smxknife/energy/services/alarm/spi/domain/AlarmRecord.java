package com.smxknife.energy.services.alarm.spi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRecord {

	private String uid;
	private String ruleUid;
	private LocalDateTime alarmTime;
	private int status;
	private String description;
	private String reporter;
}
