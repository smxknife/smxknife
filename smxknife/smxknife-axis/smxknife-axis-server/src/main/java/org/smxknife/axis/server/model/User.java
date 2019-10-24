package org.smxknife.axis.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/10/18
 */
@Getter
@Setter
@ToString
public class User implements XmlModel {
	private String name;
	private float tall;
	private float weight;
	private String sex;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", this.name);
		map.put("tall", this.tall);
		map.put("weight", this.weight);
		map.put("sex", this.sex);
		return map;
	}
}
