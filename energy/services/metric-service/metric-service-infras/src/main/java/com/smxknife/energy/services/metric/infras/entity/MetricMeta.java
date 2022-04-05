package com.smxknife.energy.services.metric.infras.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/17
 */
@Entity
@Table(name = "tb_metric_meta")
@Data
public class MetricMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String datasourceUid;

	private String metric;

	private String code;

	private String description;

	private Float ratio;
}
