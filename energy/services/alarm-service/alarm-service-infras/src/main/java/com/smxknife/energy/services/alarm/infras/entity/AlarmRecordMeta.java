package com.smxknife.energy.services.alarm.infras.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/17
 */
@Entity
@Table(name = "tb_alarm_record_meta")
@Data
public class AlarmRecordMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uid;
	private String ruleUid;
	private LocalDateTime alarmTime;
	private int status;
	private String description;
	private String reporter;
}
