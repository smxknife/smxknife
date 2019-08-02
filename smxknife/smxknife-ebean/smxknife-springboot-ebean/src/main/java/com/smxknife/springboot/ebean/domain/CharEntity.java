package com.smxknife.springboot.ebean.domain;

import javax.persistence.Entity;

/**
 * @author smxknife
 * 2019-07-18
 */
@Entity
public class CharEntity {

	char aChar;

	String string;

	String charString;

	public char getaChar() {
		return aChar;
	}

	public void setaChar(char aChar) {
		this.aChar = aChar;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getCharString() {
		return charString;
	}

	public void setCharString(String charString) {
		this.charString = charString;
	}
}
