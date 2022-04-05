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
@Table(name = "tb_ent_project")
public class Project extends EntItem {
	private String code;
	private String name;
	private String manager;

	@ManyToOne
	private Enterprise enterprise;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WorkShop> workShops;

}
