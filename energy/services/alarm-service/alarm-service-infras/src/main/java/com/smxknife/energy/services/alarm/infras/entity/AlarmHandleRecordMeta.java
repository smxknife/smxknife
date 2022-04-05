package com.smxknife.energy.services.alarm.infras.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/17
 */
@Entity
@Table(name = "tb_alarm_record_handle_meta")
@Data
public class AlarmHandleRecordMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String recordUid;
	private LocalDateTime handleTime;
	private String handler;
	private String detail;
}
