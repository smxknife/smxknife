package com.smxknife.springboot._07_jsonserializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author smxknife
 * 2021/8/1
 */
@Data
public class User {
	@JsonSerialize(using = StatusJsonSerializable.class)
	private Integer status;

	private String username;
	private String password;
}
