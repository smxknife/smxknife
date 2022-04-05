package com.smxknife.springdatajpa.model.grid;

import com.smxknife.springdatajpa.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author smxknife
 * 2020/11/19
 */
@Getter
@Setter
@Entity
@Table(name = "tb_grid")
public class Grid extends Item {

	@Column(length = 20)
	private String no;

	@Column(length = 50)
	private String name;

	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Grid parent;
}
