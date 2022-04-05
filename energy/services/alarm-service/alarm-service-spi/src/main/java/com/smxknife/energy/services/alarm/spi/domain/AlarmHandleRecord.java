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
public class AlarmHandleRecord {
	private String recordUid;
	private LocalDateTime handleTime;
	private String handler;
	private String detail;
}
