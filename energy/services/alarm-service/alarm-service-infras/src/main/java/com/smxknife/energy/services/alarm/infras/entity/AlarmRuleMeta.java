package com.smxknife.energy.services.alarm.infras.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/17
 */
@Entity
@Table(name = "tb_alarm_rule_meta")
@Data
public class AlarmRuleMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String rule;
	private String uid;
	private String type;
}
