package com.smxknife.energy.collector.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author smxknife
 * 2021/5/12
 */
@Getter
@Setter
@Entity
@Table(name = "tb_datasource_meta")
public class DatasourceMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uid;
	private String name;
	private String host;
	private Integer port;
	private String account;
	private String password;
	private String cron;
	private String type;

}
