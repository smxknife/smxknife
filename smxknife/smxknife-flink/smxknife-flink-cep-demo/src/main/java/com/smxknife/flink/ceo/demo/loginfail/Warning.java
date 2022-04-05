package com.smxknife.flink.ceo.demo.loginfail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author smxknife
 * 2020/8/31
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Warning {
	private String userId;
	private long firstFailTimestamp;
	private long lastFailTimestamp;
	private String message;
}
