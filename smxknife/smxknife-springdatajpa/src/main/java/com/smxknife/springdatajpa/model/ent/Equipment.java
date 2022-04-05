package com.smxknife.springdatajpa.model.ent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author smxknife
 * 2020/11/19
 */
@Getter
@Setter
@Entity
@Table(name = "tb_ent_equipment")
public class Equipment extends EntItem {
	private String code;
	private String name;

	@ManyToOne
	private WorkShop workShop;
}
