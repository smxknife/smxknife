package com.smxknife.java2.externalizable;

import lombok.Getter;
import lombok.Setter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author smxknife
 * 2018/11/5
 */
public class AnimalSample implements Externalizable {

	@Getter@Setter
	transient String name;
	@Getter@Setter
	int age;

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	}

	@Override
	public String toString() {
		return "{\r\n Animal:\r\n -- name = " + name + "\r\n -- age = " + age + " \r\n}";
	}
}
