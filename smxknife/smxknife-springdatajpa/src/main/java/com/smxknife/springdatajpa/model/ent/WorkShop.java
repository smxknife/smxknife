package com.smxknife.springdatajpa.model.ent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author smxknife
 * 2020/11/19
 */
@Getter
@Setter
@Entity
@Table(name = "tb_ent_workshop")
public class WorkShop extends EntItem {

	private String code;
	private String name;
	private String manager;

	@ManyToOne
	private Project project;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Equipment> equipments;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Process> processes;

}
