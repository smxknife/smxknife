package com.smxknife.springdatajpa.model.building;

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
@Table(name = "tb_building", indexes = {
		@Index(name = "idx_building_no", columnList = "no", unique = true)
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class StructureItem extends Item {

	@Column(nullable = false, length = 10)
	private String no;
	@Column(length = 50)
	private String name;
	@Column(length = 10)
	private String parent;
	private String description;
}
