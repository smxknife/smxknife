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
public class Animal2 implements Externalizable {

	@Getter@Setter
	transient String name;
	@Getter@Setter
	int age;

	public Animal2(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
		out.writeObject(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = (String) in.readObject();
		age = (int) in.readObject();
	}

	@Override
	public String toString() {
		return "{\r\n Animal:\r\n -- name = " + name + "\r\n -- age = " + age + " \r\n}";
	}
}
