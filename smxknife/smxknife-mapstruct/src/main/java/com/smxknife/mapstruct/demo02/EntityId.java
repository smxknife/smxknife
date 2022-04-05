package com.smxknife.mapstruct.demo02;

import lombok.NonNull;
import lombok.ToString;

/**
 * @author smxknife
 * 2021/3/8
 */
@ToString
public class EntityId {
	private int id;

	public EntityId(@NonNull int id) {
		this.id = id;
	}
}
