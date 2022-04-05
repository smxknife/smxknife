package com.smxknife.energy.services.metric.infras.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/17
 */
@Entity
@Table(name = "tb_datapoint_data")
@Data
public class DatapointData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String metric;
	private LocalDateTime dateTime;
	private Double value;
	private Short quality;
}
