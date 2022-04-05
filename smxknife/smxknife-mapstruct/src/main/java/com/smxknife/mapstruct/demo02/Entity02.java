package com.smxknife.mapstruct.demo02;

import lombok.Data;

/**
 * @author smxknife
 * 2021/3/8
 */
@Data
public class Entity02 {
	private EntityId entityId;
	private String name;
	private Double value;

	public EntityId getEntityId() {
		return entityId;
	}

	public void setEntityId(EntityId entityId) {
		this.entityId = entityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
