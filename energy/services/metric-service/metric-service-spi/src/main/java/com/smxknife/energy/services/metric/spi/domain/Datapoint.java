package com.smxknife.energy.services.metric.spi.domain;

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
public class Datapoint {

	private String metric;
	private LocalDateTime dateTime;
	private Double value;
	private int quality;

}
