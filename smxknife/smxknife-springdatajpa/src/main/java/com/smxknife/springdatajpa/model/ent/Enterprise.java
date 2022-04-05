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
@Table(name = "tb_ent_enterprise")
public class Enterprise extends EntItem {

	@Column(length = 30)
	private String name;
	@Column(length = 18)
	private String creditCode;
	@Column(length = 10)
	private String manager;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Project> projects;
}
