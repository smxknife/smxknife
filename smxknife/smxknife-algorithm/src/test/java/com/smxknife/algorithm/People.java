package com.smxknife.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author smxknife
 * 2019-04-29
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class People implements Comparable<People> {

	private int age;
	private String name;

	@Override
	public int compareTo(People people) {
		int i = this.age - people.getAge();
		if (i == 0) return 0;
		else if (i > 0) return 1;
		else return -1;
	}
}
