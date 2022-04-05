package com.smxknife.energy.services.metric.spi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@AllArgsConstructor
public class Metric implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String datasourceUid;

	private String metric;

	private String code;

	private String description;

	private Float ratio;
}
