package com.smxknife.algorithm.demo01.breadthfirstsearch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author smxknife
 * 2019-04-30
 */
@Getter
@Setter
@ToString(exclude = {"friends"})
@NoArgsConstructor
public class Relation implements Graph<Relation> {

	private String name;

	private boolean target;

	private List<Relation> friends;

	private boolean checked;

	public Relation(String name) {
		this.name = name;
	}

	@Override
	public List<Relation> generations() {
		return friends;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Relation)obj).name);
	}
}
